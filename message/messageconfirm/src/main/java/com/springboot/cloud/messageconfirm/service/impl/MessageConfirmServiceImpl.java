package com.springboot.cloud.messageconfirm.service.impl;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.messageconfirm.config.mq.SendFactory;
import com.springboot.cloud.messageconfirm.entity.po.MqMessage;
import com.springboot.cloud.messageconfirm.enums.MqSendStatusEnum;
import com.springboot.cloud.messageconfirm.mapper.MessageConfirmMapper;
import com.springboot.cloud.messageconfirm.service.MessageConfirmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageConfirmServiceImpl implements MessageConfirmService {

    @Autowired
    private MessageConfirmMapper messageConfirmMapper;
    @Autowired
    private SendFactory sendFactory;

    @Override
    public void saveMessageWaitingConfirm(MqMessageDto mqMessageDto) {
        if (StringUtils.isEmpty(mqMessageDto.getMessageQueue())) {
            throw new ServiceException(SystemErrorType.MESSAGE10050001);
        }
        Date now = new Date();
        MqMessage message = new MqMessage();
        BeanUtils.copyProperties(mqMessageDto,message);
        message.setMessageStatus(MqSendStatusEnum.WAIT_SEND.sendStatus());
        message.setUpdatedTime(now);
        message.setCreatedTime(now);
        messageConfirmMapper.insert(message);
    }

    @Override
    public void confirmAndSendMessage(String messageKey) {
        final MqMessage message = messageConfirmMapper.getByMessageKey(messageKey);
        if (message == null) {
            throw new ServiceException(SystemErrorType.MESSAGE10050002);
        }

        MqMessage update = new MqMessage();
        update.setMessageStatus(MqSendStatusEnum.SENDING.sendStatus());
        update.setId(message.getId());
        update.setUpdatedTime(new Date());
        messageConfirmMapper.updateById(update);

        MqMessageDto messageDto = new MqMessageDto();
        BeanUtils.copyProperties(message,messageDto);
        sendFactory.createProductPicSendMessage().send(messageDto);

    }
}
