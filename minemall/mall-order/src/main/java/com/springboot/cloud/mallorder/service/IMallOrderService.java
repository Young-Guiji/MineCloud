package com.springboot.cloud.mallorder.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.cloud.common.core.entity.form.BaseQueryForm;
import com.springboot.cloud.common.core.entity.mallorder.dto.OrderDto;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderVo;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.mallorder.entity.po.MallOrder;

public interface IMallOrderService {

    IPage queryUserOrderListWithPage(String userId, BaseQueryForm baseQueryForm);

    OrderVo createOrderDoc(UserInfoDto loginUserInfo, String shippingId);

    boolean queryOrderPayStatus(String userId, String orderNo);

    OrderDto queryOrderDtoByUserIdAndOrderNo(String userId, String orderNo);

    MallOrder queryByUserIdAndOrderNo(String userId, String orderNo);

    int cancelOrderDoc(String userId, String orderNo);

    OrderVo getOrderDetail(String userId, String orderNo);

    OrderVo getOrderDetail(String orderNo);
}
