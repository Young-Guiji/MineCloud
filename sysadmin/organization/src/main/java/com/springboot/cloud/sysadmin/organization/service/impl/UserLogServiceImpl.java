package com.springboot.cloud.sysadmin.organization.service.impl;

import com.springboot.cloud.common.core.entity.organization.dto.UserLogDto;
import com.springboot.cloud.sysadmin.organization.dao.UserLogMapper;
import com.springboot.cloud.sysadmin.organization.entity.po.UserLog;
import com.springboot.cloud.sysadmin.organization.service.IUserLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogServiceImpl implements IUserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public int addUserLog(UserLogDto userLogDto) {

        UserLog userLog = new UserLog();

        BeanUtils.copyProperties(userLogDto, userLog);

        return userLogMapper.insert(userLog);
    }
}
