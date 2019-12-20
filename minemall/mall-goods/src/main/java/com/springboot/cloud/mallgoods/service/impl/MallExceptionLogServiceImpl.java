package com.springboot.cloud.mallgoods.service.impl;

import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.mallgoods.entity.po.MallExceptionLog;
import com.springboot.cloud.mallgoods.mapper.MallExceptionLogMapper;
import com.springboot.cloud.mallgoods.service.IMallExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
@Slf4j
public class MallExceptionLogServiceImpl implements IMallExceptionLogService {

    @Autowired
    private MallExceptionLogMapper mallExceptionLogMapper;
    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void saveAndSendExceptionLog(GlobalExceptionLogDto globalExceptionLogDto) {

        MallExceptionLog exceptionLog = new MallExceptionLog();

        BeanUtils.copyProperties(globalExceptionLogDto, exceptionLog);

        exceptionLog.setCreatedTime(new Date());
        mallExceptionLogMapper.insert(exceptionLog);

        taskExecutor.execute(() -> {
            log.info("发送邮件");
        });

    }
}
