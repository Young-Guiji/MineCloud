/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：LogAspect.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.core.aspect;


import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.springboot.cloud.common.core.annotation.LogAnnotation;
import com.springboot.cloud.common.core.annotation.OperationLogDto;
import com.springboot.cloud.common.core.entity.organization.dto.UserLogDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.util.RequestUtil;
import com.springboot.cloud.common.core.util.UserContextHolder;
import com.springboot.cloud.util.JacksonUtil;
import com.springboot.cloud.util.PubUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * The class Log aspect.
 *
 * @author paascloud.net@gmail.com
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

	private ThreadLocal<Date> threadLocal = new ThreadLocal<>();

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private TaskExecutor taskExecutor;

	private static final int MAX_SIZE = 2000;

	/**
	 * Log annotation.
	 */
	@Pointcut("@annotation(com.springboot.cloud.common.core.annotation.LogAnnotation)")
	public void logAnnotation() {
	}

	/**
	 * Do before.
	 */
	@Before("logAnnotation()")
	public void doBefore() {
		this.threadLocal.set(new Date(System.currentTimeMillis()));
	}

	/**
	 * Do after.
	 *
	 * @param joinPoint   the join point
	 * @param returnValue the return value
	 */
	@AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")
	public void doAfter(final JoinPoint joinPoint, final Object returnValue) {
		if (returnValue instanceof Result) {
			Result result = (Result) returnValue;

			if (!PubUtils.isNull(result) && result.getCode() == Result.SUCCESSFUL_CODE) {
				this.handleLog(joinPoint, result);
			}

		}

	}

	private void handleLog(final JoinPoint joinPoint, final Object result) {
		final Date startTime = this.threadLocal.get();
		final Date endTime = new Date(System.currentTimeMillis());
		HttpServletRequest request = RequestUtil.getRequest();
		final UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
		String requestURI = request.getRequestURI();

		try {
			LogAnnotation relog = giveController(joinPoint);
			if (relog == null) {
				return;
			}
			String userName = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getName(),"超级管理员");
			String userId = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUserId(),"101");
			//获取客户端操作系统
			final String os = userAgent.getOs().getName();
			//获取客户端浏览器
			final String browser = userAgent.getBrowser().getName();
			final String ipAddress = RequestUtil.getRemoteAddr(request);

			UserLogDto operationLogDto = new UserLogDto();
			operationLogDto.setClassName(joinPoint.getTarget().getClass().getName());
			operationLogDto.setMethodName(joinPoint.getSignature().getName());
			operationLogDto.setExcuteTime(endTime.getTime() - startTime.getTime());
			operationLogDto.setStartTime(startTime);
			operationLogDto.setEndTime(endTime);
			operationLogDto.setIp(ipAddress);
			operationLogDto.setOs(os);
			operationLogDto.setBrowser(browser);
			operationLogDto.setRequestUrl(requestURI);

			operationLogDto.setCreatedTime(new Date());
			operationLogDto.setCreatedBy(userName);
			operationLogDto.setCreatedId(userId);
			operationLogDto.setUpdatedBy(userName);
			operationLogDto.setUpdatedId(userId);
			operationLogDto.setUpdatedTime(new Date());

			operationLogDto.setLogType(relog.logType().getType());
			operationLogDto.setLogName(relog.logType().getName());

			getControllerMethodDescription(relog, operationLogDto, result, joinPoint);
			threadLocal.remove();
			taskExecutor.execute(() -> this.restTemplate.postForObject("http://localhost:8010/userlog/add", operationLogDto, Integer.class));
		} catch (Exception ex) {
			log.error("获取注解类出现异常={}", ex.getMessage(), ex);
		}
	}


	private void getControllerMethodDescription(LogAnnotation relog, UserLogDto operationLog, Object result, JoinPoint joinPoint) {


		if (relog.isSaveRequestData()) {
			setRequestData(operationLog, joinPoint);
		}
		if (relog.isSaveResponseData()) {
			setResponseData(operationLog, result);
		}
	}

	private void setResponseData(UserLogDto requestLog, Object result) {
		try {
			requestLog.setResponseData(String.valueOf(result));
		} catch (Exception e) {
			log.error("获取响应数据,出现错误={}", e.getMessage(), e);
		}
	}

	private void setRequestData(UserLogDto uacLog, JoinPoint joinPoint) {

		try {
			Object[] args = joinPoint.getArgs();
			if (args.length == 0) {
				return;
			}
			Object[] parameter = new Object[args.length];
			int index = 0;
			for (Object object : parameter) {
				if (object instanceof HttpServletRequest) {
					continue;
				}
				parameter[index] = object;
				index++;
			}

			String requestData = JacksonUtil.toJsonWithFormat(parameter);

			if (requestData.length() > MAX_SIZE) {
				requestData = requestData.substring(MAX_SIZE);
			}

			uacLog.setRequestData(requestData);
		} catch (Exception e) {
			log.error("获取响应数据,出现错误={}", e.getMessage(), e);
		}
	}

	/**
	 * 是否存在注解, 如果存在就记录日志
	 */
	private static LogAnnotation giveController(JoinPoint joinPoint) {
		Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
		String methodName = joinPoint.getSignature().getName();
		if (null != methods && 0 < methods.length) {
			for (Method met : methods) {
				LogAnnotation relog = met.getAnnotation(LogAnnotation.class);
				if (null != relog && methodName.equals(met.getName())) {
					return relog;
				}
			}
		}

		return null;
	}

}
