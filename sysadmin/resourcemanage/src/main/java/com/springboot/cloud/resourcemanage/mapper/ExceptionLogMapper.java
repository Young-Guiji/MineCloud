package com.springboot.cloud.resourcemanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.resourcemanage.entity.po.ExceptionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {
}
