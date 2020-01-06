package com.springboot.cloud.messageconfirm.job;

import com.springboot.cloud.common.core.constant.MessageGroupConstants;
import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import com.springboot.cloud.messageconfirm.config.mq.SendFactory;
import com.springboot.cloud.messageconfirm.entity.vo.MessageVo;
import com.springboot.cloud.messageconfirm.enums.MqSendStatusEnum;
import com.springboot.cloud.messageconfirm.provider.MallGoodsFeignService;
import com.springboot.cloud.messageconfirm.service.IMessageConfirmService;
import org.quartz.JobExecutionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class ExcuteNotConfirmMessage extends QuartzJobBean {

    @Autowired
    private IMessageConfirmService messageConfirmService;
    @Autowired
    private SendFactory sendFactory;
    @Autowired
    private MallGoodsFeignService mallGoodsFeignService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {

        List<MessageVo> messageList = messageConfirmService.selectNotConfirmMessage();

        for(MessageVo messageVo : messageList){
            Result<MqMessageData> result = null;
            if(MqSendStatusEnum.WAIT_SEND.sendStatus()==messageVo.getMessageStatus()){
                if(MessageGroupConstants.GOODS_GROUP.equals(messageVo.getProducerGroup())){
                    result = mallGoodsFeignService.getMessgeByMessageKey(messageVo.getMessageKey());
                }
                if (result.getData()!=null){
                    messageConfirmService.updateMessageStatusById(messageVo.getId());
                    MqMessageDto messageDto = new MqMessageDto();
                    BeanUtils.copyProperties(messageVo,messageDto);
                    sendFactory.createSendMessage(messageVo.getMessageQueue()).send(messageDto);
                }
            }else{
                MqMessageDto messageDto = new MqMessageDto();
                BeanUtils.copyProperties(messageVo,messageDto);
                sendFactory.createSendMessage(messageVo.getMessageQueue()).send(messageDto);
            }
        }
    }
}
