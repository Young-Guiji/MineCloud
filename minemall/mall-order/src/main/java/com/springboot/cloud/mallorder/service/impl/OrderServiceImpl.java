package com.springboot.cloud.mallorder.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.springboot.cloud.common.core.constant.GlobalConstant;
import com.springboot.cloud.common.core.entity.form.BaseQueryForm;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.mallorder.constant.OrderConstant;
import com.springboot.cloud.common.core.entity.mallorder.dto.OrderDto;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderItemVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.ShippingVo;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.core.util.SnowflakeIdWorker;
import com.springboot.cloud.mallorder.constant.OrderApiConstant;
import com.springboot.cloud.mallorder.entity.po.MallCar;
import com.springboot.cloud.mallorder.entity.po.MallOrder;
import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;
import com.springboot.cloud.mallorder.entity.po.MallShipping;
import com.springboot.cloud.mallorder.mapper.MallOrderMapper;
import com.springboot.cloud.mallorder.provider.MallGoodsFeignService;
import com.springboot.cloud.mallorder.service.IMallCarService;
import com.springboot.cloud.mallorder.service.IMallOrderDetailService;
import com.springboot.cloud.mallorder.service.IMallOrderService;
import com.springboot.cloud.mallorder.service.IMallShippingService;
import com.springboot.cloud.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class OrderServiceImpl implements IMallOrderService {

    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private IMallShippingService shippingService;
    @Autowired
    private IMallOrderDetailService orderDetailService;
    @Autowired
    private IMallCarService carService;
    @Autowired
    private MallGoodsFeignService goodsFeignService;

    @Override
    public IPage queryUserOrderListWithPage(String userId, BaseQueryForm baseQueryForm) {

        Page<MallOrder> page = mallOrderMapper.selectByUserId(baseQueryForm.getPage(),userId);

        IPage<OrderVo> pageVo = page.convert(mallOrder -> assembleOrderVo(mallOrder, userId));

        return pageVo;
    }

    @Override
    public OrderVo createOrderDoc(UserInfoDto loginUserInfo, String shippingId) {

        String userId = loginUserInfo.getId();

        List<MallCar> cartList = carService.selectCheckedCartByUserId(userId);

        if (CollectionUtils.isEmpty(cartList)) {
            throw new ServiceException(SystemErrorType.ORDER10031001, userId);
        }

        //计算这个订单的总价
        List<MallOrderDetail> orderDetailList = carService.getCartOrderItem(userId, cartList);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new ServiceException(SystemErrorType.ORDER10031001, userId);
        }
        BigDecimal payment = this.getOrderTotalPrice(orderDetailList);

        //生成订单
        MallOrder order = this.assembleOrder(userId, shippingId, payment);
        if (order == null) {
            log.error("生成订单失败, userId={}, shippingId={}, payment={}", userId, shippingId, payment);
            throw new ServiceException(SystemErrorType.ORDER10031002);
        }

        for (MallOrderDetail orderDetail : orderDetailList) {
            orderDetail.setId(SnowflakeIdWorker.generateId().toString());
            orderDetail.setOrderNo(order.getOrderNo());
        }
        //批量插入订单详细信息
        orderDetailService.batchInsertOrderDetail(orderDetailList);

        //生成成功,我们要减少我们产品的库存
        this.reduceProductStock(orderDetailList);

        //清空一下购物车
        this.cleanCart(cartList);
        //返回给前端数据
        return assembleOrderVo(order, orderDetailList);

    }

    @Override
    public boolean queryOrderPayStatus(String userId, String orderNo) {
        MallOrder order = mallOrderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if (order == null) {
            throw new ServiceException(SystemErrorType.ORDER10031003);
        }
        return order.getStatus() >= OrderApiConstant.OrderStatusEnum.PAID.getCode();
    }

    @Override
    public OrderDto queryOrderDtoByUserIdAndOrderNo(String userId, String orderNo) {
        MallOrder order = this.queryByUserIdAndOrderNo(userId, orderNo);
        if (order == null) {
            throw new ServiceException(SystemErrorType.ORDER10031005, orderNo);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order,orderDto);
        return orderDto;
    }

    @Override
    public MallOrder queryByUserIdAndOrderNo(String userId, String orderNo) {
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001.getMesg());
        Preconditions.checkArgument(StringUtils.isNotEmpty(orderNo), "订单号不能为空");
        return mallOrderMapper.selectByUserIdAndOrderNo(userId, orderNo);
    }

    private void cleanCart(List<MallCar> cartList) {
        List<String> idList = Lists.newArrayList();
        for (MallCar cart : cartList) {
            idList.add(cart.getId());
        }
        int deleteCount = carService.batchDeleteCart(idList);
        if (deleteCount < idList.size()) {
            throw new ServiceException(SystemErrorType.GOOD10031006);
        }
    }

    private void reduceProductStock(List<MallOrderDetail> orderDetailList) {
        for (MallOrderDetail orderDetail : orderDetailList) {
            ProductDto product = goodsFeignService.selectProductById(orderDetail.getProductId());
            if (product == null) {
                throw new ServiceException(SystemErrorType.GOOD10021003);
            }
            product.setChangeStock(0 - orderDetail.getQuantity());

            Result<Integer> result = goodsFeignService.updateProductStockById(product);
            if (result == null) {
                throw new ServiceException(SystemErrorType.GL99990002);
            }
            if (result.isFail()) {
                throw new ServiceException(SystemErrorType.GOOD10021022, product.getId());
            }
        }
    }

    private MallOrder assembleOrder(String userId, String shippingId, BigDecimal payment) {
        MallOrder order = new MallOrder();
        long orderNo = SnowflakeIdWorker.generateId();
        order.setOrderNo(String.valueOf(orderNo));
        order.setStatus(OrderApiConstant.OrderStatusEnum.NO_PAY.getCode());
        order.setPostage(0);
        order.setPaymentType(GlobalConstant.PaymentTypeEnum.ONLINE_PAY.getCode());
        order.setPayment(payment);

        order.setUserId(userId);
        order.setShippingId(shippingId);
        //发货时间等等
        //付款时间等等
        int rowCount = mallOrderMapper.insert(order);
        if (rowCount > 0) {
            return order;
        }
        return null;
    }

    private BigDecimal getOrderTotalPrice(List<MallOrderDetail> orderDetailList) {
        BigDecimal payment = new BigDecimal("0");
        for (MallOrderDetail orderItem : orderDetailList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
        }
        return payment;
    }

    private OrderVo assembleOrderVo(MallOrder mallOrder, String userId) {
        List<MallOrderDetail> orderItemList;
        if (userId == null) {
            orderItemList = orderDetailService.getListByOrderNo(mallOrder.getOrderNo());
        } else {
            orderItemList = orderDetailService.getListByOrderNoUserId(mallOrder.getOrderNo(), userId);
        }
        OrderVo orderVo = assembleOrderVo(mallOrder, orderItemList);

        return orderVo;
    }

    private OrderVo assembleOrderVo(MallOrder order, List<MallOrderDetail> orderItemList) {
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(order.getOrderNo());
        orderVo.setPayment(order.getPayment());
        orderVo.setPaymentType(order.getPaymentType());
        orderVo.setPaymentTypeDesc(Objects.requireNonNull(GlobalConstant.PaymentTypeEnum.codeOf(order.getPaymentType())).getValue());

        orderVo.setPostage(order.getPostage());
        orderVo.setStatus(order.getStatus());
        orderVo.setStatusDesc(OrderConstant.OrderStatusEnum.codeOf(order.getStatus()).getValue());

        orderVo.setShippingId(order.getShippingId());
        MallShipping shipping = shippingService.selectById(order.getShippingId());
        if (shipping != null) {
            orderVo.setReceiverName(shipping.getReceiverName());
            orderVo.setShippingVo(assembleShippingVo(shipping));
        }

        orderVo.setPaymentTime(order.getPaymentTime());
        orderVo.setSendTime(order.getSendTime());
        orderVo.setEndTime(order.getEndTime());
        orderVo.setCreatedTime(order.getCreatedTime());
        orderVo.setCloseTime(order.getCloseTime());
        orderVo.setCreatedBy(order.getCreatedBy());

        orderVo.setImageHost("");

        List<OrderItemVo> orderItemVoList = Lists.newArrayList();

        for (MallOrderDetail orderItem : orderItemList) {
            OrderItemVo orderItemVo = assembleOrderItemVo(orderItem);
            orderItemVoList.add(orderItemVo);
        }
        orderVo.setOrderItemVoList(orderItemVoList);
        return orderVo;
    }

    private ShippingVo assembleShippingVo(MallShipping shipping) {
        ShippingVo shippingVo = new ShippingVo();
        shippingVo.setReceiverName(shipping.getReceiverName());
        shippingVo.setReceiverAddress(shipping.getDetailAddress());
        shippingVo.setReceiverProvince(shipping.getProvinceName());
        shippingVo.setReceiverCity(shipping.getCityName());
        shippingVo.setReceiverDistrict(shipping.getDistrictName());
        shippingVo.setReceiverMobile(shipping.getReceiverMobileNo());
        shippingVo.setReceiverZip(shipping.getReceiverZipCode());
        shippingVo.setReceiverPhone(shippingVo.getReceiverPhone());
        return shippingVo;
    }

    private OrderItemVo assembleOrderItemVo(MallOrderDetail orderItem) {
        log.info("订单信息 orderItem={}", orderItem);
        OrderItemVo orderItemVo = new OrderItemVo();
        orderItemVo.setOrderNo(orderItem.getOrderNo());
        orderItemVo.setProductId(orderItem.getProductId());
        orderItemVo.setProductName(orderItem.getProductName());
        // 查询商品的头图
//        String url = mdcProductService.getMainImage(orderItem.getProductId());
//        orderItemVo.setProductImage(url);
        orderItemVo.setCurrentUnitPrice(orderItem.getCurrentUnitPrice());
        orderItemVo.setQuantity(orderItem.getQuantity());
        orderItemVo.setTotalPrice(orderItem.getTotalPrice());

        orderItemVo.setCreatedTime(orderItem.getCreatedTime());
        return orderItemVo;
    }
}
