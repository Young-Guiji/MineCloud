package com.springboot.cloud.resourcemanage.service;

import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;

public interface IExceptionLogService {
    void saveAndSendExceptionLog(GlobalExceptionLogDto globalExceptionLogDto);
}
