package com.springboot.cloud.mallorder.service;

import com.springboot.cloud.common.core.entity.mallorder.vo.CartProductVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.CartVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderProductVo;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.mallorder.entity.po.MallCar;
import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;

import java.util.List;

public interface IMallCarService {
    int updateCartList(List<CartProductVo> cartProductVoList, UserInfoDto userInfo);

    CartVo getCartVo(String userId);

    int saveCart(MallCar mallCar);

    List<MallCar> selectCartListByUserId(String userId);

    OrderProductVo getOrderCartProduct(String userId);

    List<MallOrderDetail> getCartOrderItem(String userId, List<MallCar> mallCartList);

    int deleteProduct(String userId, String productIds);

    int addProduct(String userId, String productId, Integer count);

    int updateProduct(String userId, String productId, Integer count);

    int selectOrUnSelect(String userId, String productId, int checked);
}
