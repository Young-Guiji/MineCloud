package com.springboot.cloud.mallorder.service.hystrix;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import com.springboot.cloud.mallorder.service.MallExceptionLogService;
import org.springframework.stereotype.Component;

@Component
public class MallExceptionLogServiceFallback implements MallExceptionLogService {
    @Override
    public Result saveAndSendExceptionLog(GlobalExceptionLogDto exceptionLogDto) {
        return Result.fail("异常日志存储失败");
    }
}
