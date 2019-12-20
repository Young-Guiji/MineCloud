package com.springboot.cloud.common.core.exception;

/**
 * Created by Young-Guiji on 2018/6/2.
 */
public class ServiceException extends BaseException {

    //TODO 对业务异常的返回码进行校验，规范到一定范围内

    public ServiceException(SystemErrorType systemErrorType) {
        super(systemErrorType);
    }

    public ServiceException(SystemErrorType systemErrorType, Object... args) {
        super(systemErrorType, args);
    }

}
