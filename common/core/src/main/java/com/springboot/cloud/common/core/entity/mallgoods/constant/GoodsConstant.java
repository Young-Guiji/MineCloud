/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：GoodsConstant.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.core.entity.mallgoods.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The class Mdc api constant.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsConstant {

	/**
	 * The enum Product status enum.
	 *
	 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
	public enum ProductStatusEnum {
		/**
		 * On sale product status enum.
		 */
		ON_SALE(1, "在线"),
		/**
		 * Off sale product status enum.
		 */
		OFF_SALE(2, "下架"),
		/**
		 * Delete product status enum.
		 */
		DELETE(3, "删除");
		private String value;
		private int code;

		ProductStatusEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * Gets value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Gets code.
		 *
		 * @return the code
		 */
		public int getCode() {
			return code;
		}
	}
}
