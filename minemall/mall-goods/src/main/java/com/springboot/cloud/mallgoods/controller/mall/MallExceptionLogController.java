package com.springboot.cloud.mallgoods.controller.mall;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallgoods.service.IMallExceptionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(value = "API - MallExceptionLogController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(value = "/exception", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallExceptionLogController extends BaseController {

    @Autowired
    private IMallExceptionLogService mallExceptionLogService;

    @PostMapping(value = "/saveException")
    @ApiOperation(httpMethod = "POST", value = "保存日志并发送邮件")
    public Result saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto) {
        try {
            mallExceptionLogService.saveAndSendExceptionLog(exceptionLogDto);
        } catch (Exception e) {
            log.error("saveAndSendExceptionLog={}", e.getMessage(), e);
        }
        return Result.success();
    }

}
