package com.springboot.cloud.malluser.provider;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import org.springframework.stereotype.Component;

@Component
public class OrganizationFeignServiceFallback implements OrganizationFeignService {
    @Override
    public Result queryByUserId(String id) {
        return Result.success();
    }

    @Override
    public int updateUser(UserInfoDto userInfoDto) {
        return 0;
    }
}
