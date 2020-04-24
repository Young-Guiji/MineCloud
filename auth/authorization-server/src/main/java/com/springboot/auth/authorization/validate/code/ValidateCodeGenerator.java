package com.springboot.auth.authorization.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 *
	 * @param request the request
	 *
	 * @return validate code
	 */
	ValidateCode generate(ServletWebRequest request);

}
