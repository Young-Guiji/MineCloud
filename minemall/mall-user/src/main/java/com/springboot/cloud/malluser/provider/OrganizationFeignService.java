package com.springboot.cloud.malluser.provider;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "organization", fallback = OrganizationFeignServiceFallback.class)
public interface OrganizationFeignService {

    @GetMapping(value = "/user/{id}")
    Result queryByUserId(@PathVariable("id") String id);
    @PostMapping(value = "/user/updateUser")
    int updateUser(UserInfoDto userInfoDto);
}
