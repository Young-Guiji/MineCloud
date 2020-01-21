package com.springboot.cloud.mallorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.core.entity.mallgoods.constant.GoodsConstant;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.mallorder.constant.OrderConstant;
import com.springboot.cloud.common.core.entity.mallorder.vo.CartProductVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.CartVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderItemVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderProductVo;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.mallorder.entity.po.MallCar;
import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;
import com.springboot.cloud.mallorder.mapper.MallCarMapper;
import com.springboot.cloud.mallorder.service.IMallCarService;
import com.springboot.cloud.mallorder.provider.MallGoodsFeignService;
import com.springboot.cloud.util.BigDecimalUtil;
import com.springboot.cloud.util.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CarServiceImpl implements IMallCarService {

    @Autowired
    private MallGoodsFeignService mallGoodsService;
    @Autowired
    private MallCarMapper mallCarMapper;

    @Override
    public int updateCartList(List<CartProductVo> cartProductVoList, UserInfoDto userInfo) {

        log.info("updateCartList - 更新购物车集合 cartProductVoList={}", cartProductVoList);

        for (CartProductVo cartProductVo : cartProductVoList) {
            Integer quantity = cartProductVo.getQuantity();
            Integer productChecked = cartProductVo.getChecked();
            String productId = cartProductVo.getProductId();

            ProductDto productDto = mallGoodsService.selectProductById(productId);

            if (PublicUtil.isEmpty(productDto)) {
                throw new ServiceException(SystemErrorType.GOOD10021004, productId);
            }

            MallCar mallCar = new MallCar();
            mallCar.setQuantity(quantity);
            mallCar.setUserId(userInfo.getId());
            mallCar.setChecked(productChecked);
            mallCar.setProductId(productId);
            mallCar.setQuantity(quantity);

            this.saveCart(mallCar);
        }
        return 1;

    }

    @Override
    public CartVo getCartVo(String userId) {
        log.info("getCarVo -  获取购物车列表 -- userId={}", userId);
        CartVo cartVo = new CartVo();
        List<MallCar> cartList = this.selectCartListByUserId(userId);
        List<CartProductVo> cartProductVoList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");

        if (PublicUtil.isNotEmpty(cartList)) {
            for (MallCar cartItem : cartList) {
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cartItem.getId());
                cartProductVo.setUserId(userId);
                cartProductVo.setProductId(cartItem.getProductId());

                ProductDto product = mallGoodsService.selectProductById(cartItem.getProductId());

                if (product == null) {
                    throw new ServiceException(SystemErrorType.GOOD10021004, cartItem.getProductId());
                }

                cartProductVo.setProductMainImage(product.getMainImage());
                cartProductVo.setProductName(product.getName());
                cartProductVo.setProductSubtitle(product.getSubtitle());
                cartProductVo.setProductStatus(product.getStatus());
                cartProductVo.setProductPrice(product.getPrice());
                cartProductVo.setProductStock(product.getStock());

                //判断库存
                int buyLimitCount;
                if (product.getStock() >= cartItem.getQuantity()) {
                    //库存充足的时候
                    buyLimitCount = cartItem.getQuantity();
                    cartProductVo.setLimitQuantity(OrderConstant.Cart.LIMIT_NUM_SUCCESS);
                } else {
                    buyLimitCount = product.getStock();
                    cartProductVo.setLimitQuantity(OrderConstant.Cart.LIMIT_NUM_FAIL);
                    //购物车中更新有效库存
                    MallCar cartForQuantity = new MallCar();
                    cartForQuantity.setId(cartItem.getId());
                    cartForQuantity.setQuantity(buyLimitCount);
                    mallCarMapper.updateById(cartForQuantity);
                }
                cartProductVo.setQuantity(buyLimitCount);
                //计算总价
                cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(), cartProductVo.getQuantity()));
                cartProductVo.setChecked(cartItem.getChecked());

                if (cartItem.getChecked() == OrderConstant.Cart.CHECKED) {
                    //如果已经勾选,增加到整个的购物车总价中
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVoList.add(cartProductVo);
            }
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        return cartVo;
    }

    private Boolean getAllCheckedStatus(String userId) {
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);
        return mallCarMapper.selectUnCheckedCartProductCountByUserId(userId) == 0;
    }

    @Override
    public int saveCart(MallCar mallCar) {
        log.info("saveCart - 保存购物车记录 omcCart={}, userId={}", mallCar, mallCar.getUserId());

        String productId = mallCar.getProductId();
        String userId = mallCar.getUserId();
        Preconditions.checkArgument(productId != null, "货品ID不能为空");
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);

        MallCar mallCartExist = mallCarMapper.selectByProductIdAndUserId(productId, userId);
        if (PublicUtil.isEmpty(mallCartExist)) {
            try {
                return mallCarMapper.insert(mallCar);
            } catch (Exception e) {
                log.error("新增购物车, 出现异常={}", e.getMessage(), e);
            }
            return 0;
        }
        mallCar.setId(mallCartExist.getId());
        mallCar.setQuantity(mallCar.getQuantity() + mallCartExist.getQuantity());
        int updateResult = mallCarMapper.updateById(mallCar);
        if (updateResult < 1) {
            throw new ServiceException(SystemErrorType.ORDER10031014, mallCartExist.getId());
        }
        return updateResult;
    }

    @Override
    public List<MallCar> selectCartListByUserId(String userId) {
        log.info("selectCartListByUserId - 查询购物车记录 userId={}", userId);
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);
        QueryWrapper<MallCar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return mallCarMapper.selectList(queryWrapper);
    }

    @Override
    public OrderProductVo getOrderCartProduct(String userId) {
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);

        OrderProductVo orderProductVo = new OrderProductVo();

        List<MallCar> mallCartList = mallCarMapper.selectCheckedCartByUserId(userId);
        List<MallOrderDetail> orderItemList = this.getCartOrderItem(userId, mallCartList);

        List<OrderItemVo> orderItemVoList = Lists.newArrayList();
        BigDecimal payment = new BigDecimal("0");
        for (MallOrderDetail orderItem : orderItemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
//            final OptGetUrlRequest request = new OptGetUrlRequest();
//            request.setAttachmentId(Long.valueOf(orderItem.getProductImage()));
//            request.setEncrypt(true);
//            String fileUrl = opcOssService.getFileUrl(request);
            OrderItemVo orderItemVo = assembleOrderItemVo(orderItem);
            orderItemVo.setProductImage(orderItem.getProductImage());
            orderItemVoList.add(orderItemVo);
        }

        orderProductVo.setProductTotalPrice(payment);
        orderProductVo.setOrderItemVoList(orderItemVoList);

        return orderProductVo;
    }

    @Override
    public List<MallOrderDetail> getCartOrderItem(String userId, List<MallCar> mallCartList) {
        List<MallOrderDetail> orderItemList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(mallCartList)) {
            throw new ServiceException(SystemErrorType.ORDER10031001, userId);
        }

        //校验购物车的数据,包括产品的状态和数量
        for (MallCar cartItem : mallCartList) {
            MallOrderDetail orderDetail = new MallOrderDetail();
            ProductDto product = mallGoodsService.selectProductById(cartItem.getProductId());
            if (GoodsConstant.ProductStatusEnum.ON_SALE.getCode() != product.getStatus()) {
                log.error("商品不是在线售卖状态, productId={}", product.getId());
                throw new ServiceException(SystemErrorType.GOOD10021015, product.getId());
            }

            //校验库存
            if (cartItem.getQuantity() > product.getStock()) {
                log.error("商品库存不足, productId={}", product.getId());
                throw new ServiceException(SystemErrorType.GOOD10021016, product.getId());
            }

            orderDetail.setUserId(userId);
            orderDetail.setProductId(product.getId());
            orderDetail.setProductName(product.getName());
            orderDetail.setProductImage(product.getMainImage());
            orderDetail.setCurrentUnitPrice(product.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(), cartItem.getQuantity()));
            orderItemList.add(orderDetail);
        }
        return orderItemList;
    }

    @Override
    public int addProduct(String userId, String productId, Integer count) {
        MallCar mallCar = new MallCar();

        mallCar.setUserId(userId);
        mallCar.setProductId(productId);
        mallCar.setQuantity(count);
        mallCar.setChecked(OrderConstant.Cart.CHECKED);

        return this.saveCart(mallCar);
    }

    @Override
    public int updateProduct(String userId, String productId, Integer count) {
        log.info("updateProduct - 更新货品数量 userId={}, productId={}, count={}", userId, productId, count);

        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);
        Preconditions.checkArgument(productId != null, SystemErrorType.GOOD10021021);

        int resultInt;
        MallCar cart = mallCarMapper.selectByProductIdAndUserId(productId, userId);
        if (cart == null) {
            log.error("找不到商品信息, userId={}, productId={}", userId, productId);
            throw new ServiceException(SystemErrorType.GOOD10021004, productId);
        }
        if (count == 0) {
            List<String> productList = Lists.newArrayList();
            productList.add(productId);
            resultInt = mallCarMapper.deleteByUserIdProductIds(userId, productList);
        } else {
            cart.setQuantity(count);
            resultInt = mallCarMapper.updateById(cart);
        }

        return resultInt;
    }

    @Override
    public int selectOrUnSelect(String userId, String productId, int checked) {
        log.info("selectOrUnSelect - 选中购物车记录 userId={}, productId={}, checked={}", userId, productId, checked);

        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);

        return mallCarMapper.checkedOrUncheckedProduct(userId, productId, checked);
    }

    @Override
    public List<MallCar> selectCheckedCartByUserId(String userId) {
        return mallCarMapper.selectCheckedCartByUserId(userId);
    }

    @Override
    public int batchDeleteCart(List<String> idList) {
        return mallCarMapper.batchDeleteCart(idList);
    }

    @Override
    public int deleteProduct(String userId, String productIds) {
        log.info("deleteProduct - 删除购物车记录 userId={}, productIds={}", userId, productIds);
        Preconditions.checkArgument(StringUtils.isNotEmpty(productIds), SystemErrorType.GOOD10021021);
        List<String> productList = Splitter.on(",").splitToList(productIds);
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);
        return mallCarMapper.deleteByUserIdProductIds(userId, productList);
    }

    private OrderItemVo assembleOrderItemVo(MallOrderDetail orderItem) {
        OrderItemVo orderItemVo = new OrderItemVo();
        orderItemVo.setOrderNo(orderItem.getOrderNo());
        orderItemVo.setProductId(orderItem.getProductId());
        orderItemVo.setProductName(orderItem.getProductName());
        orderItemVo.setProductImage(orderItem.getProductImage());
        orderItemVo.setCurrentUnitPrice(orderItem.getCurrentUnitPrice());
        orderItemVo.setQuantity(orderItem.getQuantity());
        orderItemVo.setTotalPrice(orderItem.getTotalPrice());
        orderItemVo.setCreatedTime(orderItem.getCreatedTime());
        return orderItemVo;
    }
}
