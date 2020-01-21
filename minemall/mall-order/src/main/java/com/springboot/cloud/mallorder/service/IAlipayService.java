package com.springboot.cloud.mallorder.service;

import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.core.entity.vo.Result;

public interface IAlipayService {
    Result pay(String orderNo, UserInfoDto loginUserInfo);
}
