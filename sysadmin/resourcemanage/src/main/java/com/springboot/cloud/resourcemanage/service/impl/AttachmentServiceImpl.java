package com.springboot.cloud.resourcemanage.service.impl;

import com.springboot.cloud.common.core.constant.MqTopicConstants;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UpdateAttachmentDto;
import com.springboot.cloud.resourcemanage.entity.po.Attachment;
import com.springboot.cloud.resourcemanage.mapper.AttachmentMapper;
import com.springboot.cloud.resourcemanage.service.AttachmentService;
import com.springboot.cloud.util.JacksonUtil;
import com.springboot.cloud.util.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public void handlerProductPicMessage(String body, String tags) {

        if (StringUtils.equals(tags, MqTopicConstants.MqTagEnum.DELETE_ATTACHMENT.getTag())) {
            List<String> idList = attachmentMapper.queryAttachmentByRefNo(body);
            for (final String id : idList) {
//                this.deleteFile(id);
            }
        } else {
            UpdateAttachmentDto attachmentDto;
            try {
                attachmentDto = JacksonUtil.parseJson(body, UpdateAttachmentDto.class);
            } catch (Exception e) {
                log.error("发送短信MQ出现异常={}", e.getMessage(), e);
                throw new IllegalArgumentException("JSON转换异常", e);
            }
            this.updateAttachment(attachmentDto);
        }

    }

    @Override
    public void updateAttachment(UpdateAttachmentDto attachmentDto) {
        List<String> attachmentIdList = attachmentDto.getAttachmentIdList();
        String refNo = attachmentDto.getRefNo();
        List<String> idList = attachmentMapper.queryAttachmentByRefNo(refNo);
        if (PublicUtil.isNotEmpty(idList)) {
            idList.removeAll(attachmentIdList);
            for (final String id : idList) {
//                this.deleteFile(id);
            }
        }
        for (final String id : attachmentIdList) {
            Attachment attachment = new Attachment();
            attachment.setId(id);
            attachment.setRefNo(refNo);
            attachmentMapper.updateById(attachment);
        }
    }
}
