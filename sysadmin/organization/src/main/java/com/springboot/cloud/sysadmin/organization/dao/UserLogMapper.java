package com.springboot.cloud.sysadmin.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.sysadmin.organization.entity.po.UserLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLogMapper extends BaseMapper<UserLog> {

}
