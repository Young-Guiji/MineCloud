package com.springboot.cloud.common.core.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MqMessageDataMapper extends BaseMapper<MqMessageData> {
    MqMessageData getMessgeByMessageKey(String messageKey);
}
