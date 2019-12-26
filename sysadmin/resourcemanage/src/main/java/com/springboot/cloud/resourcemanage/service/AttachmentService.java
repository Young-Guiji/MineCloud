package com.springboot.cloud.resourcemanage.service;

import com.springboot.cloud.common.core.entity.resourcemanage.dto.UpdateAttachmentDto;

public interface AttachmentService {
    void handlerProductPicMessage(String body, String tags);

    void updateAttachment(UpdateAttachmentDto attachmentDto);
}
