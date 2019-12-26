package com.springboot.cloud.messageconfirm.config.mq;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.cloud.common.core.constant.MqTopicConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProducerConfig {

    @Bean
    Queue queue() {
        log.info("queue name:{}", MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue());
        return new Queue(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue(), false);
    }

    @Bean
    TopicExchange exchange() {
        log.info("exchange:{}",  MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getExchange());
        return new TopicExchange(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getExchange());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        log.info("binding {} to {} with {}", queue, exchange, MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue());
        return BindingBuilder.bind(queue).to(exchange).with(MqTopicConstants.MqTopicEnum.SEND_SMS_QUEUE.getQueue());
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

}
