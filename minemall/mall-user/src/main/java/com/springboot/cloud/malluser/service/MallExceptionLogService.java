package com.springboot.cloud.malluser.service;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.malluser.service.hystrix.MallExceptionLogServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mall-goods",fallback = MallExceptionLogServiceFallback.class)
public interface MallExceptionLogService {

    @PostMapping(value = "/exception/saveException")
    Result saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto);
}
