package com.springboot.cloud.messageconfirm.controller;


import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.messageconfirm.service.IMessageConfirmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MessageConfirmController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MessageConfirmController extends BaseController {

    @Autowired
    private IMessageConfirmService messageConfirmService;

    @ApiOperation(httpMethod = "POST", value = "预存储消息")
    @PostMapping("/saveMessageWaitingConfirm")
    public Result saveMessageWaitingConfirm(@RequestBody MqMessageDto mqMessageDto){
        logger.info("预存储消息. mqMessageDto={}", mqMessageDto);
        messageConfirmService.saveMessageWaitingConfirm(mqMessageDto);
        return Result.success();
    }

    @ApiOperation(httpMethod = "POST", value = "确认并发送消息")
    @PostMapping("/confirmAndSendMessage")
    public Result confirmAndSendMessage(@RequestParam("messageKey") String messageKey){
        logger.info("确认并发送消息. messageKey={}", messageKey);
        messageConfirmService.confirmAndSendMessage(messageKey);
        return Result.success();
    }

    @ApiOperation(httpMethod = "POST", value = "确认消费消息")
    @PostMapping("/confirmFinishMessage")
    public Result confirmFinishMessage(@RequestParam("messageKey") String messageKey){
        logger.info("确认消费消息. messageKey={}", messageKey);
        messageConfirmService.confirmFinishMessage(messageKey);
        return Result.success();
    }

}
