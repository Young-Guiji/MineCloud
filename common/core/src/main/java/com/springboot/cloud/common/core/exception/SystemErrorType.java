package com.springboot.cloud.common.core.exception;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType {

    SYSTEM_ERROR("-1", "系统异常"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),
    SYS99990002("99990002", "微服务不在线,或者网络超时"),

    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    GATEWAY_ERROR("010500", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("010002", "网关超时"),

    ARGUMENT_NOT_VALID("020000", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("020001", "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY("030000","唯一键冲突"),

    GL99990002("99990002", "微服务不在线,或者网络超时"),

    USER10011001("10011001", "用户Id不能为空"),

    GOOD10021003("10021003", "获取商品信息失败"),

    GOOD10021004("10021004", "找不到该商品信息,productId=%s"),

    GOOD10021015("10021015", "商品不是在线售卖状态, productId=%s"),

    GOOD10021016("10021016", "商品库存不足, productId=%s"),

    GOOD10021017("10021017", "产品已下架或者删除, productId=%s"),

    GOOD10021021("10021021", "商品ID不能为空"),

    GOOD10021022("10021022", "更新商品信息失败, productId=%s"),

    GOOD10021023("10021023", "删除商品信息失败, productId=%s"),

    GOOD10021024("10021024", "商品编码不能为空"),

    ORDER10031001("10031001", "购物车为空, userId=%s"),

    ORDER10031002("10031002", "生成订单失败"),

    ORDER10031003("10031003", "该用户此订单不存在"),

    ORDER10031005("10031005", "找不到订单信息, orderNo=%s"),

    ORDER10031009("10031009", "批量插入订单明细失败"),

    ORDER10031014("10031014", "更新购物车数据失败, cartId=%s"),

    ORDER10031004("10031004", "已付款, 无法取消订单"),

    ORDER10031006("10031006", "清空购物车失败"),

    GOOD10031006("10031006", "清空购物车失败"),

    MESSAGE10050001("10050001", "消息的消费Topic不能为空"),

    MESSAGE10050002("10050002", "根据消息key查找的消息为空"),

    MESSAGE10050004("10050004", "消息中心接口异常,message=%s, messageKey=%s"),

    MESSAGE10050005("10050005", "目标接口参数不能为空"),

    MESSAGE10050007("10050007", "消息数据不能为空"),

    MESSAGE10050008("10050008", "消息体不能为空,messageKey=%s"),

    MESSAGE10050009("10050009", "消息KEY不能为空"),

    MESSAGE100500015("10050015", "消息PID不能为空, messageKey=%s"),

    MESSAGE100500030("10050030", "定时任务 targetClass:{%s} 已存在，请勿重复创建 !"),

    MESSAGE100500031("10050031", "触发器 triggerName:{%s} 已存在，请勿重复创建 !"),

    MESSAGE100500032("10050032", "修改任务失败"),

    MESSAGE100500033("10050033", "查询批处理任务失败"),

    INVALID_HEADER("10011040", "解析header失败");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    SystemErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
