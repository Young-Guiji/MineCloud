package com.springboot.cloud.mallorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MallOrderDetailMapper extends BaseMapper<MallOrderDetail> {
    List<MallOrderDetail> getListByOrderNo(String orderNo);

    List<MallOrderDetail> getListByOrderNoUserId(@Param("orderNo") String orderNo, @Param("userId") String userId);

    int batchInsertOrderDetail(@Param("orderDetailList") List<MallOrderDetail> orderDetailList);
}
