package com.springboot.cloud.common.core.entity.message.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MqMessageDto implements Serializable {


    private static final long serialVersionUID = -6980935654952282538L;

     private String id;
     /**
     * 生产者组
     */
    private String producerGroup;

    /**
     * 业务单据号
     */
    private String refNo;

    /**
     * 消息key
     */
    private String messageKey;

    /**
     * topic
     */
    private String messageQueue;

    /**
     * tag
     */
    private String messageTag;

    /**
     * 消息类型
     */
    private Integer messageType;

    /**
     * 消息内容
     */
    private String messageBody;

    /**
     * 延时消息的延时级别
     */
    private int delayLevel;

    /**
     * 顺序类型, 0有序 1无序
     */
    private int orderType;

}
