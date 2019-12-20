package com.springboot.cloud.malluser.service.hystrix;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.malluser.service.OrganizationService;
import org.springframework.stereotype.Component;

@Component
public class OrganizationServiceFallback implements OrganizationService {
    @Override
    public Result queryByUserId(String id) {
        return Result.success();
    }

    @Override
    public int updateUser(UserInfoDto userInfoDto) {
        return 0;
    }
}
