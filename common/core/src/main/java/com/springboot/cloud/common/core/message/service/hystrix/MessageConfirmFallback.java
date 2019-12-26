package com.springboot.cloud.common.core.message.service.hystrix;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.message.service.MessageConfirmFeignClient;
import org.springframework.stereotype.Component;

@Component
public class MessageConfirmFallback implements MessageConfirmFeignClient {
    @Override
    public void saveMessageWaitingConfirm(MqMessageDto mqMessageDto) {

    }

    @Override
    public Result confirmAndSendMessage(String messageKey) {
        return null;
    }
}
