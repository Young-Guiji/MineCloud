/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：AsyncTaskProperties.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */
package com.springboot.cloud.common.core.thread.properties;

import com.springboot.cloud.common.core.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The class Async task properties.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@Data
@Component
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class AsyncTaskProperties {

	private int corePoolSize = 50;

	private int maxPoolSize = 100;

	private int queueCapacity = 10000;

	private int keepAliveSeconds = 3000;

	private String threadNamePrefix = "mine-task-executor-";
}
