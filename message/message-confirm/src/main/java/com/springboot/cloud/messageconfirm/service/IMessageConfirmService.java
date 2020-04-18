package com.springboot.cloud.messageconfirm.service;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.messageconfirm.entity.vo.MessageVo;

import java.util.List;

public interface IMessageConfirmService {
    void saveMessageWaitingConfirm(MqMessageDto mqMessageDto);

    void confirmAndSendMessage(String messageKey);

    void confirmFinishMessage(String messageKey);

    List<MessageVo> selectNotConfirmMessage();

    void updateMessageStatusById(String id);
}
