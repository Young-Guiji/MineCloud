package com.springboot.cloud.sysadmin.organization.entity.po;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_log")
public class UserLog extends BasePo {


    private static final long serialVersionUID = -3326236838689347547L;
    /**
     * 组织流水号
     */
    private Long groupId;

    /**
     * 组织名称
     */
    private String groupName;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 日志类型名称
     */
    private String logName;

    /**
     * 权限ID
     */
    private Long actionId;

    /**
     * 权限编码
     */
    private String actionCode;

    /**
     * 权限名称
     */
    private String actionName;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 物理地址
     */
    private String mac;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 请求参数
     */
    private String requestData;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 响应结果
     */
    private String responseData;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 耗时,秒
     */
    private Long excuteTime;
    /**
     * 登录位置
     */
    private String location;

}
