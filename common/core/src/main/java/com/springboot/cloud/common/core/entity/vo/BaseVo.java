package com.springboot.cloud.common.core.entity.vo;

import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class BaseVo<T extends BasePo> implements Serializable {
    public final static String DEFAULT_USERNAME = "system";
    private String id;
    /**
     * 版本号
     */
    private Integer version;
    private String createdId;
    private String updatedId;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}
