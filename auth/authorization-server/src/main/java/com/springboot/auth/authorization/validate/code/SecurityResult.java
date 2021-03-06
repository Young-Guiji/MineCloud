/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：SecurityResult.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.auth.authorization.validate.code;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * The class Security result.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@Data
@NoArgsConstructor
public class SecurityResult {

	/**
	 * 成功码.
	 */
	public static final String SUCCESS_CODE = "200";

	/**
	 * 成功信息.
	 */
	public static final String SUCCESS_MESSAGE = "操作成功";

	/**
	 * 错误码.
	 */
	public static final String ERROR_CODE = "500";

	/**
	 * 错误信息.
	 */
	public static final String ERROR_MESSAGE = "内部异常";

	/**
	 * 状态码
	 */
    private String code;

	/**
	 * 提示信息
	 */
	private String message;

	/**
	 * 结果
	 */
    private Object result;

	public static SecurityResult ok(Object data) {
        return new SecurityResult(data);
    }

	public static SecurityResult ok() {
        return new SecurityResult(null);
    }

	public static SecurityResult error(String message) {
		return error(message, null);
	}

	public static SecurityResult error(String message, Object data) {
		return new SecurityResult(ERROR_CODE, StringUtils.isEmpty(message) ? ERROR_MESSAGE : message, data);
	}

	public SecurityResult(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

	private SecurityResult(Object result) {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
        this.result = result;
    }
}
