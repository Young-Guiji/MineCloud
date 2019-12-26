package com.springboot.cloud.common.core.entity.resourcemanage.dto;

import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class UpdateAttachmentDto implements Serializable {
    private static final long serialVersionUID = -768471033009336091L;

    public UpdateAttachmentDto() {

    }

    private String refNo;
    /**
     * 商品图片流水号集合
     */
    private List<String> attachmentIdList;

}