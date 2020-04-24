package com.springboot.cloud.mallgoods.controller.message;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import com.springboot.cloud.common.core.message.service.MqMessageService;
import com.springboot.cloud.common.web.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Mdc dict main controller.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@RestController
@RequestMapping(value = "/message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MessageController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MessageController  extends BaseController {

    @Autowired
    private MqMessageService mqMessageService;

    @GetMapping("/getMessgeByMessageKey/{messageKey}")
    @ApiOperation(httpMethod = "GET", value = "获取消息")
    public Result<MqMessageData> getMessgeByMessageKey(@PathVariable String messageKey){
        MqMessageData mqMessageData = mqMessageService.getMessgeByMessageKey(messageKey);
        return Result.success(mqMessageData);
    }

}
