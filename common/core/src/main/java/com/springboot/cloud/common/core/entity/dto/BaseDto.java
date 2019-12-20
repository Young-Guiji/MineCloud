package com.springboot.cloud.common.core.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDto implements Serializable {

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
