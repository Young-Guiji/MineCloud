package com.springboot.auth.authorization.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * QQ登录配置项
 *
 * @author guiji
 */
@Data
public class QQProperties{

	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 qq。
	 */
	private String providerId = "qq";

}
