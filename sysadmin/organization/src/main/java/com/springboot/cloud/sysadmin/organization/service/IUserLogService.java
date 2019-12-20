package com.springboot.cloud.sysadmin.organization.service;

import com.springboot.cloud.common.core.entity.organization.dto.UserLogDto;

public interface IUserLogService {
    int addUserLog(UserLogDto userLogDto);
}
