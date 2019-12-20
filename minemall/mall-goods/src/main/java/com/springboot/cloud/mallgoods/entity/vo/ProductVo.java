/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：ProductVo.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.mallgoods.entity.vo;


import com.springboot.cloud.common.core.entity.dto.ElementImgUrlDto;
import com.springboot.cloud.common.core.entity.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * The class MallProduct vo.
 *
 * @author guiji
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductVo extends BaseVo {
	private static final long serialVersionUID = 5719854343599455196L;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品编码
	 */
	private String productCode;

	/**
	 * 商品副标题
	 */
	private String subtitle;

	/**
	 * 产品主图,url相对地址
	 */
	private String mainImage;

	/**
	 * 价格,单位-元保留两位小数
	 */
	private BigDecimal price;

	/**
	 * 库存数量
	 */
	private Integer stock;

	/**
	 * 商品状态.1-在售 2-下架 3-删除
	 */
	private Integer status;

	/**
	 * 图片地址,json格式,扩展用
	 */
	private String subImages;

	/**
	 * 商品详情
	 */
	private String detail;

	/**
	 * 分类ID
	 */
	private List<String> categoryIdList;

	/**
	 * 图片集合
	 */
	private List<ElementImgUrlDto> imgUrlList;
}
