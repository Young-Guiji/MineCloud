///*
// * Copyright (c) 2018. paascloud.net All Rights Reserved.
// * 项目名称：paascloud快速搭建企业级分布式微服务平台
// * 类名称：AsyncTaskExecutorConfiguration.java
// * 创建人：刘兆明
// * 联系方式：guiji
// * 开源地址: https://github.com/paascloud
// * 博客地址: http://blog.paascloud.net
// * 项目官网: http://paascloud.net
// */
//
//package com.springboot.cloud.common.core.thread.config;
//
//import com.springboot.cloud.common.core.thread.properties.AsyncTaskProperties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.AsyncConfigurer;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
///**
// * The class Async config.
// *
// * @author guiji
// */
//@Configuration
//@EnableAsync
//@EnableScheduling
//public class AsyncTaskExecutorConfiguration implements AsyncConfigurer {
//	private final Logger log = LoggerFactory.getLogger(getClass());
//
//	@Autowired
//	private AsyncTaskProperties asyncTaskProperties;
//
//	@Override
//	@Bean(name = "taskExecutor")
//	public Executor getAsyncExecutor() {
//		log.debug("Creating Async Task Executor");
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(asyncTaskProperties.getCorePoolSize());
//		executor.setMaxPoolSize(asyncTaskProperties.getMaxPoolSize());
//		executor.setQueueCapacity(asyncTaskProperties.getQueueCapacity());
//		executor.setKeepAliveSeconds(asyncTaskProperties.getKeepAliveSeconds());
//		executor.setThreadNamePrefix(asyncTaskProperties.getThreadNamePrefix());
//		return new ExceptionHandlingAsyncTaskExecutor(executor);
//	}
//
//	@Override
//	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//		return new SimpleAsyncUncaughtExceptionHandler();
//	}
//}
