/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：MallExceptionLog.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.mallgoods.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * The class Mdc exception log.
 *
 * @author guiji
 */
@Alias("mdcExceptionLog")
@TableName("pc_mdc_exception_log")
@Data
@NoArgsConstructor
public class MallExceptionLog {
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	/**
	 * 系统应用名
	 */
	private String applicationName;

	/**
	 * 异常类型
	 */
	private String exceptionSimpleName;

	/**
	 * 异常信息(通过exception.getMessage()获取到的内容)
	 */
	private String exceptionMessage;

	/**
	 * 异常原因(通过exception.getCause()获取到的内容)
	 */
	private String exceptionCause;

	/**
	 * 异常堆栈信息
	 */
	private String exceptionStack;

	/**
	 * 操作者姓名
	 */
	private String createdBy;

	/**
	 * 操作者id
	 */
	private String createdId;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;
}