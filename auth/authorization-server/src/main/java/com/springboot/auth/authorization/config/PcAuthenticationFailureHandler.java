package com.springboot.auth.authorization.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.auth.authorization.exception.AuthErrorType;
import com.springboot.auth.authorization.validate.code.ValidateCodeException;
import com.springboot.auth.authorization.validate.code.ValidateCodeFilter;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * APP环境下认证失败处理器
 *
 * @author guiji
 */
@Component("pcAuthenticationFailureHandler")
public class PcAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * On authentication failure.
	 *
	 * @param request   the request
	 * @param response  the response
	 * @param exception the exception
	 *
	 * @throws IOException      the io exception
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

		logger.info("登录失败");

		// 记录失败次数 和原因 ip等信息 5次登录失败,锁定用户, 不允许在此登录

		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
		logger.error(exception.getMessage());
		Result result = Result.fail(exception.getMessage());
		if (exception instanceof BadCredentialsException) {
			result = Result.fail(AuthErrorType.INVALID_USER);
		}else if(exception instanceof ValidateCodeException){
			result = Result.fail(AuthErrorType.INVALID_VALIDATECODE);
		}
		response.getWriter().write(objectMapper.writeValueAsString(result));

	}

}
