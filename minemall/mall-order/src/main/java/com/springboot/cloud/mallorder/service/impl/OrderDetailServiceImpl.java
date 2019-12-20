package com.springboot.cloud.mallorder.service.impl;

import com.google.common.base.Preconditions;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;
import com.springboot.cloud.mallorder.mapper.MallOrderDetailMapper;
import com.springboot.cloud.mallorder.service.IMallOrderDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements IMallOrderDetailService {

    @Autowired
    private MallOrderDetailMapper mallOrderDetailMapper;

    @Override
    public List<MallOrderDetail> getListByOrderNo(String orderNo) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(orderNo), "订单号不能为空");
        return mallOrderDetailMapper.getListByOrderNo(orderNo);
    }

    @Override
    public List<MallOrderDetail> getListByOrderNoUserId(String orderNo, String userId) {
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);
        Preconditions.checkArgument(StringUtils.isNotEmpty(orderNo), "订单号不能为空");
        return mallOrderDetailMapper.getListByOrderNoUserId(orderNo, userId);
    }
}
