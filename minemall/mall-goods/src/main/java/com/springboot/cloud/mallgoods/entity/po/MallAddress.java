/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：MallAddress.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.mallgoods.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Mdc address.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("pc_mall_address")
public class MallAddress extends BasePo {
	private static final long serialVersionUID = -499010884211394846L;

	/**
	 * 地址名称
	 */
	private String name;

	/**
	 * 父ID
	 */
	private Long pid;

	/**
	 * 城市编码
	 */
	private String cityCode;

	/**
	 * 级别（省市区县）
	 */
	private Integer level;

	/**
	 * 区域编码
	 */
	private String adCode;

	/**
	 * 行政区边界坐标点
	 */
	private String polyline;

	/**
	 * 城市中心点
	 */
	private String center;
}
