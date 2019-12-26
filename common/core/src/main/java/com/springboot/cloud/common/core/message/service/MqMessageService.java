package com.springboot.cloud.common.core.message.service;

import com.springboot.cloud.common.core.message.entity.po.MqMessageData;

public interface MqMessageService {
    void saveWaitConfirmMessage(MqMessageData mqMessageData);

    void saveMqProducerMessage(MqMessageData mqMessageData);

    void confirmAndSendMessage(String messageKey);
}
