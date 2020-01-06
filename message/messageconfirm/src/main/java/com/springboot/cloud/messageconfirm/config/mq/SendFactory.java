package com.springboot.cloud.messageconfirm.config.mq;

import com.springboot.cloud.common.core.constant.MqTopicConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendFactory {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public SendMessage createSendMessage(String queueName){
        if(MqTopicConstants.PRODUCT_PIC_QUEUE.equals(queueName)){
            return createProductPicSendMessage();
        }else if(MqTopicConstants.SEND_EMAIL_QUEUE.equals(queueName)){
            return createSMSSendMessage();
        }else if(MqTopicConstants.SEND_SMS_QUEUE.equals(queueName)){
            return null;
        }
        return null;
    }

    public SendMessage createSMSSendMessage(){
        return new SMSSendMessage(rabbitTemplate);
    }

    public SendMessage createProductPicSendMessage(){
        return new ProductPicSendMessage(rabbitTemplate);
    }



}
