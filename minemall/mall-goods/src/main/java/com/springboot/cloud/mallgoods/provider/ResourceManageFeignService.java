package com.springboot.cloud.mallgoods.provider;

import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "resource-manage",fallback = ResourceManageFeignServiceFallback.class)
public interface ResourceManageFeignService {

    @PostMapping(value = "/exception/saveException")
    Result saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto);
}
