/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：UserInfoDto.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.core.entity.malluser.dto;

import com.springboot.cloud.common.core.entity.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Set;

/**
 * The class User info dto.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@Data
@ApiModel(value = "用户Dto")
public class UserInfoDto extends BaseDto {

	private static final long serialVersionUID = -889913964833331690L;
	private String name;
	private String mobile;
	private String username;
	private String description;
	private String deleted;
	private Set<String> roleIds;

}
