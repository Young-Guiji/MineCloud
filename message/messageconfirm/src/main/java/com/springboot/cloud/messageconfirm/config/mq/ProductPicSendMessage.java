package com.springboot.cloud.messageconfirm.config.mq;

import com.springboot.cloud.common.core.constant.MqTopicConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ProductPicSendMessage implements SendMessage {

    private RabbitTemplate rabbitTemplate;

    public ProductPicSendMessage(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(Object message) {
        rabbitTemplate.convertAndSend(MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getExchange(),
                MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue(),
                message);
    }
}
