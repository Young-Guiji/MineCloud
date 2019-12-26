/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：MqMessageData.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.core.message.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class Mq message data.
 *
 * @author paascloud.net @gmail.com
 */
@TableName("pc_mq_message_data")
@Data
@NoArgsConstructor
public class MqMessageData extends BasePo {

	/**
	 * 消息key
	 */
	private String messageKey;

	/**
	 * topic
	 */
	private String messageQueue;

	/**
	 * tag
	 */
	private String messageTag;

	/**
	 * 消息内容
	 */
	private String messageBody;

	/**
	 * 消息类型: 10 - 生产者 ; 20 - 消费者
	 */
	private Integer messageType;

	/**
	 * 顺序类型, 0有序 1无序
	 */
	private int orderType;

	/**
	 * 消息状态
	 */
	private Integer status;

	/**
	 * 延时级别
	 */
	private int delayLevel;

	/**
	 * 是否删除 -0 未删除 -1 已删除
	 */
	private Integer yn;

	/**
	 * producer group name
	 */
	@TableField(exist = false)
	private String producerGroup;

	public MqMessageData(final String msgBody, final String queue, final String tag, final String key) {
		this.messageBody = msgBody;
		this.messageQueue = queue;
		this.messageTag = tag;
		this.messageKey = key;
	}

	/**
	 * Gets tpc mq message dto.
	 *
	 * @return the tpc mq message dto
	 */
	public MqMessageDto getMqMessageDto() {
		MqMessageDto mqMessageDto = new MqMessageDto();
		mqMessageDto.setMessageBody(this.messageBody);
		mqMessageDto.setMessageKey(this.messageKey);
		mqMessageDto.setMessageTag(this.messageTag);
		mqMessageDto.setMessageQueue(this.messageQueue);
		mqMessageDto.setMessageType(this.messageType);
		mqMessageDto.setRefNo(this.messageKey);
		mqMessageDto.setDelayLevel(this.delayLevel);
		mqMessageDto.setOrderType(this.orderType);
		mqMessageDto.setProducerGroup(this.producerGroup);
		return mqMessageDto;
	}
}