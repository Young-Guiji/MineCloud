package com.springboot.auth.authorization.service;

import com.springboot.auth.authorization.entity.User;
import com.springboot.auth.authorization.oauth2.userdetail.SecurityUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface IUserService {

    /**
     * 根据用户唯一标识获取用户信息
     *
     * @param uniqueId
     * @return
     */
    @Cacheable(value = "#id")
    User getByUniqueId(String uniqueId);

    /**
     * 存储用户登录信息
     * @return
     */
    void handlerLoginData(OAuth2AccessToken token, SecurityUser principal, HttpServletRequest request);
}
