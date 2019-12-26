package com.springboot.cloud.messageconfirm.service;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;

public interface MessageConfirmService {
    void saveMessageWaitingConfirm(MqMessageDto mqMessageDto);

    void confirmAndSendMessage(String messageKey);
}
