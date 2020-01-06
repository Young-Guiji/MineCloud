package com.springboot.cloud.common.core.aspect;

import com.springboot.cloud.common.core.entity.message.dto.MqMessageDto;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.core.message.provider.MessageConfirmFeignService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MqConsumerStoreAspect {

    @Autowired
    private MessageConfirmFeignService messageConfirmFeignService;

    /**
     * Add exe time annotation pointcut.
     */
    @Pointcut("@annotation(com.springboot.cloud.common.core.annotation.MqConsumerStore)")
    public void mqConsumerStoreAnnotationPointcut() {

    }


    @Around(value = "mqConsumerStoreAnnotationPointcut()")
    public Object processMqConsumerStoreJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("processMqConsumerStoreJoinPoint - 线程id={}", Thread.currentThread().getId());
        Object result;
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();

        if (args == null || args.length == 0) {
            throw new ServiceException(SystemErrorType.MESSAGE10050005);
        }

        MqMessageDto mqMessage = (MqMessageDto) args[0];

        final String messageKey = mqMessage.getMessageKey();

        String methodName = joinPoint.getSignature().getName();
        try {
            result = joinPoint.proceed();
            log.info("result={}", result);
            messageConfirmFeignService.confirmFinishMessage(messageKey);
        } catch (Exception e) {
            log.error("发送可靠消息, 目标方法[{}], 出现异常={}", methodName, e.getMessage(), e);
            throw e;
        } finally {
            log.info("发送可靠消息 目标方法[{}], 总耗时={}", methodName, System.currentTimeMillis() - startTime);
        }
        return result;
    }

}
