/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：BaseController.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.common.web.support;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.util.UserContextHolder;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.util.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * The class Base controller.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handle result wrapper.
	 *
	 * @param <T>    the type parameter
	 * @param result the result
	 *
	 * @return the wrapper
	 */
	protected <T> Result<T> handleResult(T result) {

		boolean flag = isFlag(result);

		if (flag) {
			return Result.success(result);
		} else {
			return Result.fail(result);
		}
	}

	/**
	 * Handle result wrapper.
	 *
	 * @param <E>      the type parameter
	 * @param result   the result
	 * @param errorMsg the error msg
	 *
	 * @return the wrapper
	 */
	protected <E> Result<E> handleResult(E result, String errorMsg) {
		boolean flag = isFlag(result);

		if (flag) {
			return Result.success(result);
		} else {
			return Result.fail(result);
		}
	}

	private boolean isFlag(Object result) {
		boolean flag;
		if (result instanceof Integer) {
			flag = (Integer) result > 0;
		} else if (result instanceof Boolean) {
			flag = (Boolean) result;
		} else {
			flag = PublicUtil.isNotEmpty(result);
		}
		return flag;
	}

	protected UserInfoDto getLoginUserInfo(){
		Map<String, String> context = UserContextHolder.getInstance().getContext();
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setId(context.get("userId"));
		userInfoDto.setUsername(context.get("user_name"));
		userInfoDto.setName(context.get("loginName"));
		return userInfoDto;
	}


//	protected long generateId() {
//		return UniqueIdGenerator.getInstance(IncrementIdGenerator.getServiceId()).nextId();
//	}

}
