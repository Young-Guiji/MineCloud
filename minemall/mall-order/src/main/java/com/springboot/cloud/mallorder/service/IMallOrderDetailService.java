package com.springboot.cloud.mallorder.service;

import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;

import java.util.List;

public interface IMallOrderDetailService {
    List<MallOrderDetail> getListByOrderNo(String orderNo);

    List<MallOrderDetail> getListByOrderNoUserId(String orderNo, String userId);

    void batchInsertOrderDetail(List<MallOrderDetail> orderDetailList);
}
