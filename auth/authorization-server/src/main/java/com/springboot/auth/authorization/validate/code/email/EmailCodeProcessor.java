/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：EmailCodeProcessor.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.auth.authorization.validate.code.email;

import com.springboot.auth.authorization.properties.SecurityConstants;
import com.springboot.auth.authorization.validate.code.AbstractValidateCodeProcessor;
import com.springboot.auth.authorization.validate.code.ValidateCode;
import com.springboot.auth.authorization.validate.code.ValidateCodeGenerator;
import com.springboot.auth.authorization.validate.code.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


import java.util.Map;

/**
 * 短信验证码处理器
 *
 * @author guiji
 */
@Component("emailValidateCodeProcessor")
public class EmailCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private EmailCodeSender emailCodeSender;

	/**
	 * Instantiates a new Abstract validate code processor.
	 *
	 * @param validateCodeGenerators the validate code generators
	 * @param validateCodeRepository the validate code repository
	 */
	public EmailCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		super(validateCodeGenerators, validateCodeRepository);
	}

	/**
	 * Send.
	 *
	 * @param request      the request
	 * @param validateCode the validate code
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_EMAIL;
		String email = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		emailCodeSender.send(email, validateCode.getCode());
	}
}
