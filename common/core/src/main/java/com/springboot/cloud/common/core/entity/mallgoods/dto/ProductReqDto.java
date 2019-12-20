/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：ProductReqDto.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.core.entity.mallgoods.dto;


import com.springboot.cloud.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Product req dto.
 *
 * @author guiji
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class ProductReqDto extends BaseQueryForm {
	private static final long serialVersionUID = -9070173642901418263L;
	@ApiModelProperty(value = "分类ID")
	private String categoryId;
	@ApiModelProperty(value = "关键词")
	private String keyword;
}
