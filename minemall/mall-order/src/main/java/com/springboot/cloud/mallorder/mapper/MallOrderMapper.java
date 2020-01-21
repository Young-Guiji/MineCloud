package com.springboot.cloud.mallorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.mallorder.entity.po.MallOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MallOrderMapper extends BaseMapper<MallOrder> {
    Page<MallOrder> selectByUserId(Page page, @Param("userId") String userId);

    MallOrder selectByUserIdAndOrderNo(String userId, String orderNo);
}
