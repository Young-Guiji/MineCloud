package com.springboot.auth.authorization.properties;

/**
 * 认证成功后的响应方式
 *
 * @author guiji
 */
public enum LoginResponseType {

	/**
	 * 跳转
	 */
	REDIRECT,
	/**
	 * 返回json
	 */
	JSON

}
