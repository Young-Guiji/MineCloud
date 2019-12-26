package com.springboot.cloud.messageconfirm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.messageconfirm.entity.po.MqMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageConfirmMapper extends BaseMapper<MqMessage> {
    MqMessage getByMessageKey(String messageKey);
}
