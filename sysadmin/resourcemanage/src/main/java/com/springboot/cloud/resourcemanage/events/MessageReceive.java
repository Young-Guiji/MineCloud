package com.springboot.cloud.resourcemanage.events;

import com.google.common.base.Preconditions;
import com.springboot.cloud.common.core.annotation.MqConsumerStore;
import com.springboot.cloud.common.core.constant.MqTopicConstants;
import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.resourcemanage.service.AttachmentService;
import com.springboot.cloud.util.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MessageReceive {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private AttachmentService attachmentService;


    @RabbitListener(queues = MqTopicConstants.SEND_SMS_QUEUE)
    public void processSMS(MqMessageDto mqMessage) {
        log.info("SMSReceive"+ mqMessage);
        processMessage(mqMessage);
    }

    @RabbitListener(queues = MqTopicConstants.PRODUCT_PIC_QUEUE)
    @MqConsumerStore
    public void processProductPic(MqMessageDto mqMessage) {
        log.info("ProductPicReceive"+ mqMessage);

        processMessage(mqMessage);
    }

    private void processMessage(MqMessageDto mqMessage) {

        this.checkMessage(mqMessage);

        String body = mqMessage.getMessageBody();
        String queue = mqMessage.getMessageQueue();
        String tags = mqMessage.getMessageTag();
        String key = mqMessage.getMessageKey();

        log.info("MQ消费queue={},tag={},key={}", queue, tags, key);
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        // 控制幂等性使用的key
        Object mqKV = null;
        if (redisTemplate.hasKey(key)) {
            mqKV = ops.get(key);
        }
        if (PublicUtil.isNotEmpty(mqKV)) {
            log.error("MQ消费queue={},tag={},key={}, 重复消费", queue, tags, key);
        }
        log.info("MQ消费queue={},tag={},key={}", queue, tags, key);

        if(queue.equals(MqTopicConstants.PRODUCT_PIC_QUEUE)){
            attachmentService.handlerProductPicMessage(body,tags);
        }else if(queue.equals(MqTopicConstants.SEND_SMS_QUEUE)){

        }
        ops.set(key, key, 10, TimeUnit.DAYS);
    }

    private void checkMessage(MqMessageDto messageDto) {
        Preconditions.checkArgument(!StringUtils.isEmpty(messageDto.getMessageQueue()), "发送消息失败, 消息主题不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(messageDto.getMessageKey()), "发送消息失败, 消息关键字不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(messageDto.getMessageBody()), "发送消息失败, 消息体不能为空");
    }
}
