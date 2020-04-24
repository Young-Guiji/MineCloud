/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：TpcMqMessage.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.messageconfirm.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The class Tpc mq message.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@Data
@Alias(value = "mqMessage")
@TableName("pc_mq_message")
public class MqMessage extends BasePo {
	private static final long serialVersionUID = -5951754367474682967L;

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
	 * 消息类型: 10 - 生产者 ; 20 - 消费者
	 */
	private Integer messageType;

	/**
	 * 生产者PID
	 */
	private String producerGroup;

	/**
	 * 延时级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
	 */
	private Integer delayLevel;

	/**
	 * 顺序类型 0有序 1无序
	 */
	private Integer orderType;

	/**
	 * 消息状态
	 */
	private Integer messageStatus;

	/**
	 * 消息内容
	 */
	private String messageBody;

	/**
	 * 状态
	 */
	private Integer taskStatus;

	/**
	 * 执行次数
	 */
	private Integer resendTimes;

	/**
	 * 是否死亡 0 - 活着; 1-死亡
	 */
	private Integer dead;

	/**
	 * 执行时间
	 */
	private Integer nextExeTime;

	/**
	 * 是否删除 -0 未删除 -1 已删除
	 */
	private Integer yn;

	@TableField(exist = false)
	private List<Integer> preStatusList;
}
