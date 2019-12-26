package com.springboot.cloud.common.core.message.provider;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.message.provider.MessageConfirmFeignClient;
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
