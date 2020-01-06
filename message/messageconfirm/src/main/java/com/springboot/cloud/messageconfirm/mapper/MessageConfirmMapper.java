package com.springboot.cloud.messageconfirm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.messageconfirm.entity.po.MqMessage;
import com.springboot.cloud.messageconfirm.entity.vo.MessageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageConfirmMapper extends BaseMapper<MqMessage> {
    MqMessage getByMessageKey(String messageKey);

    void confirmFinishMessage(String messageKey);

    List<MessageVo> selectNotConfirmMessage();

    void updateMessageStatusById(String id);
}
