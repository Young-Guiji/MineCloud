package com.springboot.cloud.resourcemanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.resourcemanage.entity.po.Attachment;

import java.util.List;

public interface AttachmentMapper extends BaseMapper<Attachment> {
    List<String> queryAttachmentByRefNo(String body);
}
