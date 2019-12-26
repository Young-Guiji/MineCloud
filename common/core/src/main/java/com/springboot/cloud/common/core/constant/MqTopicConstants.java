/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：AliyunMqTopicConstants.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */
package com.springboot.cloud.common.core.constant;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The class Aliyun mq topic constants.
 *
 * @author paascloud.net @gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MqTopicConstants {

	public static final String SEND_SMS_QUEUE = "SEND_SMS_QUEUE";

	public static final String SEND_EMAIL_QUEUE = "SEND_EMAIL_QUEUE";

	public static final String TPC_QUEUE = "TPC_QUEUE";

	public static final String OPC_QUEUE = "OPC_QUEUE";

	public static final String PRODUCT_PIC_QUEUE = "PRODUCT_PIC_QUEUE";

	/**
	 * The enum Uac mq topic enum.
	 *
	 * @author paascloud.net @gmail.com
	 */
	public enum MqTopicEnum {

		/**
		 * 发送短信.
		 */
		SEND_SMS_QUEUE(MqTopicConstants.SEND_SMS_QUEUE, "SEND_SMS_EXCHANGE","发送短信"),
		/**
		 * 发送邮件.
		 */
		SEND_EMAIL_QUEUE(MqTopicConstants.SEND_EMAIL_QUEUE, "SEND_EMAIL_EXCHANGE","发送邮件"),

		TPC_QUEUE(MqTopicConstants.TPC_QUEUE, "TPC_EXCHANGE","TPC_TOPIC"),

		OPC_QUEUE(MqTopicConstants.OPC_QUEUE, "OPC_EXCHANGE","OPC_TOPIC"),

		PRODUCT_PIC_QUEUE(MqTopicConstants.PRODUCT_PIC_QUEUE, "PRODUCT_PIC_EXCHANGE","MDC_TOPIC"),;

		MqTopicEnum(String queue, String exchange, String queueName) {
			this.queue = queue;
			this.exchange = exchange;
			this.queueName = queueName;
		}

		/**
		 * The queue.
		 */
		String queue;
		/**
		 * The exchange.
		 */
		String exchange;
		/**
		 * The queue name.
		 */
		String queueName;

		/**
		 * Gets queue.
		 *
		 * @return the queue
		 */
		public String getQueue() {
			return queue;
		}

		public String getExchange() {
			return exchange;
		}

	}


	/**
	 * The enum Uac mq tag enum.
	 *
	 * @author paascloud.net @gmail.com
	 */
	public enum MqTagEnum {

		/**
		 * 注册获取验证码.
		 */
		REGISTER_USER_AUTH_CODE("REGISTER_USER_AUTH_CODE", MqTopicEnum.SEND_SMS_QUEUE.getQueue(), "注册获取验证码"),
		/**
		 * 修改密码获取验证码.
		 */
		MODIFY_PASSWORD_AUTH_CODE("MODIFY_PASSWORD_AUTH_CODE", MqTopicEnum.SEND_SMS_QUEUE.getQueue(), "修改密码获取验证码"),
		/**
		 * 忘记密码获取验证码.
		 */
		FORGOT_PASSWORD_AUTH_CODE("FORGOT_PASSWORD_AUTH_CODE", MqTopicEnum.SEND_EMAIL_QUEUE.getQueue(), "忘记密码获取验证码"),

		/**
		 * 激活用户.
		 */
		ACTIVE_USER("ACTIVE_USER", MqTopicEnum.SEND_EMAIL_QUEUE.getQueue(), "激活用户"),
		/**
		 * 激活用户成功.
		 */
		ACTIVE_USER_SUCCESS("ACTIVE_USER_SUCCESS", MqTopicEnum.SEND_EMAIL_QUEUE.getQueue(), "激活用户成功"),
		/**
		 * 重置密码
		 */
		RESET_LOGIN_PWD("RESET_LOGIN_PWD", MqTopicEnum.SEND_EMAIL_QUEUE.getQueue(), "重置密码"),

		/**
		 * 重置密码
		 */
		RESET_USER_EMAIL("RESET_LOGIN_PWD", MqTopicEnum.SEND_EMAIL_QUEUE.getQueue(), "重置密码"),

		/**
		 * 删除生产者历史消息
		 */
		DELETE_PRODUCER_MESSAGE("DELETE_PRODUCER_MESSAGE", MqTopicEnum.TPC_QUEUE.getQueue(), "删除生产者历史消息"),

		/**
		 * 删除消费者历史消息
		 */
		DELETE_CONSUMER_MESSAGE("DELETE_CONSUMER_MESSAGE", MqTopicEnum.TPC_QUEUE.getQueue(), "删除消费者历史消息"),

		/**
		 * 发送异常日志.
		 */
		SEND_DINGTALK_MESSAGE("SEND_EXCEPTION_LOG", MqTopicEnum.OPC_QUEUE.getQueue(), "发送异常日志"),

		/**
		 * 更新附件信息.
		 */
		UPDATE_ATTACHMENT("UPDATE_ATTACHMENT", MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue(), "更新附件信息"),
		/**
		 * 删除附件信息
		 */
		DELETE_ATTACHMENT("DELETE_ATTACHMENT", MqTopicEnum.PRODUCT_PIC_QUEUE.getQueue(), "删除附件信息"),;
		/**
		 * The Tag.
		 */
		String tag;
		/**
		 * The Topic.
		 */
		String queue;
		/**
		 * The Tag name.
		 */
		String tagName;

		MqTagEnum(String tag, String queue, String tagName) {
			this.tag = tag;
			this.queue = queue;
			this.tagName = tagName;
		}

		/**
		 * Gets tag.
		 *
		 * @return the tag
		 */
		public String getTag() {
			return tag;
		}

		/**
		 * Gets topic.
		 *
		 * @return the topic
		 */
		public String getQueue() {
			return queue;
		}
	}

}
