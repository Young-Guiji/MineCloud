package com.springboot.cloud.malluser.service;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.malluser.service.hystrix.OrganizationServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "organization", fallback = OrganizationServiceFallback.class)
public interface OrganizationService {

    @GetMapping(value = "/user/{id}")
    Result queryByUserId(@PathVariable("id") String id);
    @PostMapping(value = "/user/updateUser")
    int updateUser(UserInfoDto userInfoDto);
}
