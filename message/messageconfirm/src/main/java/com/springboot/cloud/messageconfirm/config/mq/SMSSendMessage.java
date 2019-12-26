package com.springboot.cloud.messageconfirm.config.mq;

import com.springboot.cloud.common.core.constant.MqTopicConstants;
import org.springframework.amqp.core.AmqpTemplate;

public class SMSSendMessage implements SendMessage{

    private AmqpTemplate amqpTemplate;

    public SMSSendMessage(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void send(Object message) {
        amqpTemplate.convertAndSend(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getExchange(),
                MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue(),
                message);
    }
}
