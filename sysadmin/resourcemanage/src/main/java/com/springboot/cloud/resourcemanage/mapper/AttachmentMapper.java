package com.springboot.cloud.resourcemanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.AttachmentDto;
import com.springboot.cloud.resourcemanage.entity.po.Attachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentMapper extends BaseMapper<Attachment> {
    List<String> queryAttachmentByRefNo(String body);

    List<AttachmentDto> queryAttachment(Attachment optAttachment);
}
