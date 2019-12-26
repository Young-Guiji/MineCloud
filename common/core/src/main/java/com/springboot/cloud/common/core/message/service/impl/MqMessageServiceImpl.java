package com.springboot.cloud.common.core.message.service.impl;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.core.message.entity.enums.MqMessageTypeEnum;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import com.springboot.cloud.common.core.message.mapper.MqMessageDataMapper;
import com.springboot.cloud.common.core.message.service.MessageConfirmFeignClient;
import com.springboot.cloud.common.core.message.service.MqMessageService;
import com.springboot.cloud.common.core.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqMessageServiceImpl implements MqMessageService {

    @Autowired
    private MqMessageDataMapper mqMessageDataMapper;
    @Autowired
    private MessageConfirmFeignClient messageConfirmService;

    @Override
    public void saveMqProducerMessage(MqMessageData mqMessageData) {
        // 校验消息数据
        this.checkMessage(mqMessageData);
        // 先保存消息
        mqMessageData.setMessageType(MqMessageTypeEnum.PRODUCER_MESSAGE.messageType());
        mqMessageData.setId(String.valueOf(SnowflakeIdWorker.generateId()));
        mqMessageDataMapper.insert(mqMessageData);
    }

    @Override
    public void confirmAndSendMessage(String messageKey) {
        Result result = messageConfirmService.confirmAndSendMessage(messageKey);
        if (result == null) {
            throw new ServiceException(SystemErrorType.SYS99990002);
        }
        if (result.isFail()) {
            throw new ServiceException(SystemErrorType.MESSAGE10050004, result.getMesg(), messageKey);
        }
        log.info("<== saveMqProducerMessage - 存储并发送消息给消息中心成功. messageKey={}", messageKey);
    }

    @Override
    public void saveWaitConfirmMessage(MqMessageData mqMessageData) {
        this.saveMqProducerMessage(mqMessageData);
        MqMessageDto mqMessageDto = mqMessageData.getMqMessageDto();
        messageConfirmService.saveMessageWaitingConfirm(mqMessageDto);
        log.info("<== saveWaitConfirmMessage - 存储预发送消息成功. messageKey={}", mqMessageData.getMessageKey());
    }

    private void checkMessage(MqMessageData mqMessageData) {
        if (null == mqMessageData) {
            throw new ServiceException(SystemErrorType.MESSAGE10050007);
        }
        String messageQueue = mqMessageData.getMessageQueue();
        String messageBody = mqMessageData.getMessageBody();
        String messageKey = mqMessageData.getMessageKey();
        String producerGroup = mqMessageData.getProducerGroup();
        if (StringUtils.isEmpty(messageKey)) {
            throw new ServiceException(SystemErrorType.MESSAGE10050009);
        }
        if (StringUtils.isEmpty(messageQueue)) {
            throw new ServiceException(SystemErrorType.MESSAGE10050001);
        }
        if (StringUtils.isEmpty(messageBody)) {
            throw new ServiceException(SystemErrorType.MESSAGE10050008, mqMessageData.getMessageKey());
        }

        if (StringUtils.isEmpty(producerGroup)) {
            throw new ServiceException(SystemErrorType.MESSAGE100500015, mqMessageData.getMessageKey());
        }
    }
}
