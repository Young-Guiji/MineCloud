package com.springboot.cloud.mallgoods.service;

import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;

public interface IMallExceptionLogService {
    void saveAndSendExceptionLog(GlobalExceptionLogDto globalExceptionLogDto);
}
