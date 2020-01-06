package com.springboot.cloud.messageconfirm.config.mq;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.common.core.constant.MqTopicConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProducerConfig {

    @Bean
    @Qualifier("smsqueue")
    Queue smsqueue() {
        log.info("queue name:{}", MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue());
        return new Queue(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue(), false);
    }

    @Bean
    @Qualifier("smsexchange")
    TopicExchange smsexchange() {
        log.info("exchange:{}",  MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getExchange());
        return new TopicExchange(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getExchange());
    }

    @Bean
    @Qualifier("smsbinding")
    Binding smsbinding(Queue smsqueue, TopicExchange smsexchange) {
        log.info("binding {} to {} with {}", smsqueue, smsexchange, MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue());
        return BindingBuilder.bind(smsqueue).to(smsexchange).with(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue());
    }

    @Bean
    @Qualifier("productqueue")
    Queue productqueue() {
        log.info("queue name:{}", MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue());
        return new Queue(MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue(), false);
    }

    @Bean
    @Qualifier("productexchange")
    TopicExchange productexchange() {
        log.info("exchange:{}",  MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getExchange());
        return new TopicExchange(MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getExchange());
    }

    @Bean
    @Qualifier("productbinding")
    Binding productbinding(Queue productqueue, TopicExchange productexchange) {
        log.info("binding {} to {} with {}", productqueue, productexchange, MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue());
        return BindingBuilder.bind(productqueue).to(productexchange).with(MqTopicConstants.MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue());
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
