package com.springboot.cloud.malluser.exception;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import com.springboot.cloud.malluser.service.MallExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @Autowired
    private TaskExecutor taskExecutor;
    @Value("${spring.profiles.active}")
    String profile;
    @Value("${spring.application.name}")
    String applicationName;
    @Autowired
    private MallExceptionLogService mallExceptionLogService;

    @Override
    public Result exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        taskExecutor.execute(() -> {
            GlobalExceptionLogDto globalExceptionLogDto = new GlobalExceptionLogDto().getGlobalExceptionLogDto(e, profile, applicationName);
            mallExceptionLogService.saveAndSendExceptionLog(globalExceptionLogDto);
        });

        return super.exception(e);
    }
}