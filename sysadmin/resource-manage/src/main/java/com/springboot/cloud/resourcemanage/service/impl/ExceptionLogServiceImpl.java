package com.springboot.cloud.resourcemanage.service.impl;

import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.resourcemanage.entity.po.ExceptionLog;
import com.springboot.cloud.resourcemanage.mapper.ExceptionLogMapper;
import com.springboot.cloud.resourcemanage.service.IExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ExceptionLogServiceImpl implements IExceptionLogService {

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;
    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void saveAndSendExceptionLog(GlobalExceptionLogDto globalExceptionLogDto) {

        ExceptionLog exceptionLog = new ExceptionLog();

        BeanUtils.copyProperties(globalExceptionLogDto, exceptionLog);

        exceptionLog.setCreatedTime(new Date());
        exceptionLogMapper.insert(exceptionLog);

        taskExecutor.execute(() -> {
            log.info("发送邮件");
        });

    }
}
