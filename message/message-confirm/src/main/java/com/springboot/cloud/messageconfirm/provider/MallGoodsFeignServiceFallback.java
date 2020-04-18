package com.springboot.cloud.messageconfirm.provider;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import org.springframework.stereotype.Component;

@Component
public class MallGoodsFeignServiceFallback implements MallGoodsFeignService {

    @Override
    public Result<MqMessageData> getMessgeByMessageKey(String messageKey) {
        return Result.success();
    }
}
