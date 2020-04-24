/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：Perm.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.core.entity.malluser.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Perm.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@Data
public class Perm implements Serializable {

	private static final long serialVersionUID = 6578617729619284277L;
	private String resource;
	private String perm;

}
