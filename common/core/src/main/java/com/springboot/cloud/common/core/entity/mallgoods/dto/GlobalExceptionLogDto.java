/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：GlobalExceptionLogDto.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.core.entity.mallgoods.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.cloud.common.core.constant.GlobalConstant;
import com.springboot.cloud.common.core.util.UserContextHolder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;

/**
 * The class Global exception log dto.
 *
 * @author guiji
 */
@Data
@NoArgsConstructor
@Slf4j
public class GlobalExceptionLogDto {

	/**
	 * 运行环境
	 */
	private String profile;

	/**
	 * 应用名称
	 */
	private String applicationName;

	/**
	 * 异常信息(通过exception.getMessage()获取到的内容)
	 */
	private String exceptionMessage;

	/**
	 * 异常原因(通过exception.getCause()获取到的内容)
	 */
	private String exceptionCause;

	/**
	 * 异常类型
	 */
	private String exceptionSimpleName;
	/**
	 * 异常堆栈信息
	 */
	private String exceptionStack;

	/**
	 * 创建人
	 */
	private String createdBy;

	/**
	 * 创建人ID
	 */
	private String createdId;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;

	/**
	 * Gets global exception log dto.
	 *
	 * @param ex the ex
	 *
	 * @return the global exception log dto
	 */
	public GlobalExceptionLogDto getGlobalExceptionLogDto(Exception ex, String profile, String applicationName) {
		String message = ex.getMessage();
		if (StringUtils.isNotBlank(message) && message.length() > GlobalConstant.EXCEPTION_MESSAGE_MAX_LENGTH) {
			this.exceptionMessage = StringUtils.substring(message, 0, GlobalConstant.EXCEPTION_MESSAGE_MAX_LENGTH) + "...";
		}
		this.exceptionSimpleName = ex.getClass().getSimpleName();
		String cause = ex.getCause() == null ? null : ex.getCause().toString();
		if (StringUtils.isNotBlank(cause) && cause.length() > GlobalConstant.EXCEPTION_CAUSE_MAX_LENGTH) {
			this.exceptionCause = StringUtils.substring(cause, 0, GlobalConstant.EXCEPTION_CAUSE_MAX_LENGTH) + "...";
		}
		this.exceptionStack = Arrays.toString(ex.getStackTrace());

		this.profile = profile;

		this.createdId = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getName(), "101");
		this.createdBy = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getName(), "超级管理员");
		this.applicationName = applicationName;
		return this;
	}
}