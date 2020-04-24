/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：CategoryVo.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.mallgoods.entity.vo;

import com.springboot.cloud.common.core.entity.dto.BaseTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Mdc dict vo.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryVo extends BaseTree {
	private static final long serialVersionUID = 5310269803239754048L;
	/**
	 * 父分类名称
	 */
	private String parentCategoryName;
	/**
	 * 字典类型 -0 常量 - 1 文件夹
	 */
	private Integer type;

	/**
	 * 字典名称
	 */
	private String name;

	/**
	 * 序号
	 */
	private Integer number;

	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 排序编号,同类展示顺序,数值相等则自然排序
	 */
	private Integer sortOrder;
	/**
	 * 分类编码
	 */
	private String categoryCode;
}
