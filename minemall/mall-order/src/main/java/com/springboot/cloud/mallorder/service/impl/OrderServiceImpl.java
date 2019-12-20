package com.springboot.cloud.mallorder.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.springboot.cloud.common.core.constant.GlobalConstant;
import com.springboot.cloud.common.core.entity.form.BaseQueryForm;
import com.springboot.cloud.common.core.entity.mallorder.constant.OrderConstant;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderItemVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.ShippingVo;
import com.springboot.cloud.mallorder.entity.po.MallOrder;
import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;
import com.springboot.cloud.mallorder.entity.po.MallShipping;
import com.springboot.cloud.mallorder.mapper.MallOrderMapper;
import com.springboot.cloud.mallorder.service.IMallOrderDetailService;
import com.springboot.cloud.mallorder.service.IMallOrderService;
import com.springboot.cloud.mallorder.service.IMallShippingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage queryUserOrderListWithPage(String userId, BaseQueryForm baseQueryForm) {

        Page<MallOrder> page = mallOrderMapper.selectByUserId(baseQueryForm.getPage(),userId);

        IPage<OrderVo> pageVo = page.convert(mallOrder -> assembleOrderVo(mallOrder, userId));

        return pageVo;
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
