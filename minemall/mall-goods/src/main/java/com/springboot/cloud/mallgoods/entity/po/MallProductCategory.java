/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：MallProductCategory.java
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
import org.apache.ibatis.type.Alias;

/**
 * The class Mdc product category.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("pc_mall_product_category")
@Alias("productCategory")
public class MallProductCategory extends BasePo {

	private static final long serialVersionUID = 6966836239624795099L;

	/**
	 * 分类编码
	 */
	private String categoryCode;

	/**
	 * 首图的流水号
	 */
	private Long imgId;

	/**
	 * 父类别id当id=0时说明是根节点,一级类别
	 */
	private String pid;

	/**
	 * 类别名称
	 */
	private String name;

	/**
	 * 类别状态1-启用,2-禁用
	 * {@link }
	 */
	private Integer status;

	/**
	 * 排序编号,同类展示顺序,数值相等则自然排序
	 */
	private Integer sortOrder;

}
