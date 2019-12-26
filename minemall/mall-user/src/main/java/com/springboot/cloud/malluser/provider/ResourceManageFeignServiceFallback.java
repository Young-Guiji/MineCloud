package com.springboot.cloud.malluser.provider;

import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Component;

@Component
public class ResourceManageFeignServiceFallback implements ResourceManageFeignService {
    @Override
    public Result saveAndSendExceptionLog(GlobalExceptionLogDto exceptionLogDto) {
        return Result.fail("异常日志存储失败");
    }
}
