package com.springboot.cloud.messageconfirm.config.mq;

import com.springboot.cloud.common.core.constant.MqTopicConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SMSSendMessage implements SendMessage{

    private RabbitTemplate rabbitTemplate;

    public SMSSendMessage(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(Object message) {
        rabbitTemplate.convertAndSend(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getExchange(),
                MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue(),
                message);
    }
}
