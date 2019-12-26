package com.springboot.cloud.messageconfirm.config.mq;

import com.springboot.cloud.common.core.constant.MqTopicConstants;
import org.springframework.amqp.core.AmqpTemplate;

public class ProductPicSendMessage implements SendMessage {

    private AmqpTemplate amqpTemplate;

    public ProductPicSendMessage(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void send(Object message) {
        amqpTemplate.convertAndSend(MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getExchange(),
                MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue(),
                message);
    }
}
