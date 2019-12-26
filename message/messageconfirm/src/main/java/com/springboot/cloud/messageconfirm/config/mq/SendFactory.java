package com.springboot.cloud.messageconfirm.config.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendFactory {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public SendMessage createSMSSendMessage(){
        return new SMSSendMessage(amqpTemplate);
    }

    public SendMessage createProductPicSendMessage(){
        return new ProductPicSendMessage(amqpTemplate);
    }



}
