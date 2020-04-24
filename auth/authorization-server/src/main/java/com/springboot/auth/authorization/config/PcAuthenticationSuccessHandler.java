package com.springboot.auth.authorization.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.auth.authorization.oauth2.userdetail.SecurityUser;
import com.springboot.auth.authorization.service.IUserService;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * APP环境下认证成功处理器.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@Component("pcAuthenticationSuccessHandler")
@Slf4j
public class PcAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private IUserService userService;
	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;

	private static final String BEARER_TOKEN_TYPE = "Basic ";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}

		String[] tokens = RequestUtil.extractAndDecodeHeader(header);
		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];
//$2a$10$2szDKjvKHJCWE6YQNznogOeQF3USZHmCYj1fG7YbfK.vnTgNKLzri
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
		} else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
		}

		TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

		OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
		SecurityUser principal = (SecurityUser) authentication.getPrincipal();
		userService.handlerLoginData(token, principal, request);

		log.info("用户【 {} 】记录登录日志", principal.getUsername());

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write((objectMapper.writeValueAsString(Result.success(token))));

	}

}
