package com.springboot.auth.authorization.service.impl;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.springboot.auth.authorization.entity.User;
import com.springboot.auth.authorization.oauth2.userdetail.SecurityUser;
import com.springboot.auth.authorization.provider.OrganizationProvider;
import com.springboot.auth.authorization.service.IUserService;
import com.springboot.cloud.common.core.constant.LogTypeEnum;
import com.springboot.cloud.common.core.entity.organization.dto.UserLogDto;
import com.springboot.cloud.common.core.util.RequestUtil;
import com.springboot.cloud.util.GaoDeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class UserService implements IUserService {

    @Value("${minemall.http.isProxy}")
    private boolean isProxy;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }

    @Override
    public void handlerLoginData(OAuth2AccessToken token, SecurityUser principal, HttpServletRequest request) {

        final UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOs().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtil.getRemoteAddr(request);
        // 根据IP获取位置信息
        final String remoteLocation = GaoDeUtil.getCityByIpAddr(remoteAddr,isProxy);
        final String requestURI = request.getRequestURI();

        UserLogDto userLogDto = new UserLogDto();
        userLogDto.setIp(remoteAddr);
        userLogDto.setLocation(remoteLocation);
        userLogDto.setOs(os);
        userLogDto.setBrowser(browser);
        userLogDto.setRequestUrl(requestURI);
        userLogDto.setLogType(LogTypeEnum.LOGIN_LOG.getType());
        userLogDto.setLogName(LogTypeEnum.LOGIN_LOG.getName());
        userLogDto.setCreatedBy(principal.getUsername());
        userLogDto.setCreatedTime(new Date());

        taskExecutor.execute(() -> organizationProvider.saveUserLog(userLogDto));

    }
}
