package com.springboot.cloud.mallorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.mallorder.entity.po.MallShipping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MallShippingMapper extends BaseMapper<MallShipping> {

    Page<MallShipping> selectByUserId(Page<MallShipping> page,@Param("userId") String userId);
}
