package com.springboot.cloud.messageconfirm.provider;


import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mall-goods",fallback = MallGoodsFeignServiceFallback.class)
public interface MallGoodsFeignService {
    @GetMapping(value = "/message/getMessgeByMessageKey/{messageKey}")
    Result getMessgeByMessageKey(@PathVariable("messageKey")  String messageKey);
}
