package com.springboot.cloud.common.core.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class BasePo implements Serializable {

    public final static String DEFAULT_USERNAME = "超级管理员";
    public final static String DEFAULT_USERID= "101";
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String createdBy = DEFAULT_USERNAME;
    private String updatedBy = DEFAULT_USERNAME;
    private Date createdTime = Date.from(ZonedDateTime.now().toInstant());
    private Date updatedTime = Date.from(ZonedDateTime.now().toInstant());
    /**
     * 版本号
     */
    private Integer version;
    private String createdId;
    private String updatedId;
}
