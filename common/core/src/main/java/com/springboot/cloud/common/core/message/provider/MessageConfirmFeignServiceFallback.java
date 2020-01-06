package com.springboot.cloud.common.core.message.provider;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Component;

@Component
public class MessageConfirmFeignServiceFallback implements MessageConfirmFeignService {
    @Override
    public void saveMessageWaitingConfirm(MqMessageDto mqMessageDto) {

    }

    @Override
    public Result confirmAndSendMessage(String messageKey) {
        return null;
    }

    @Override
    public Result confirmFinishMessage(String messageKey) {
        return null;
    }
}
