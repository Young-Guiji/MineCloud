package com.springboot.cloud.common.core.message.provider;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "message-confirm", fallback = MessageConfirmFeignServiceFallback.class)
public interface MessageConfirmFeignService {

    @PostMapping(value = "/message/saveMessageWaitingConfirm")
    void saveMessageWaitingConfirm(@RequestBody MqMessageDto mqMessageDto);

    @PostMapping(value = "/message/confirmAndSendMessage")
    Result confirmAndSendMessage(@RequestParam("messageKey") String messageKey);

    @PostMapping(value = "/message/confirmFinishMessage")
    Result confirmFinishMessage(@RequestParam("messageKey") String messageKey);
}
