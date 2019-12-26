package com.springboot.cloud.common.core.aspect;

import com.springboot.cloud.common.core.annotation.MqProducerStore;
import com.springboot.cloud.common.core.message.entity.enums.DelayLevelEnum;
import com.springboot.cloud.common.core.message.entity.enums.MqSendTypeEnum;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.core.message.service.MqMessageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class MqProducerStoreAspect {

    @Autowired
    private MqMessageService mqMessageService;
    @Autowired
    private TaskExecutor taskExecutor;


    @Pointcut("@annotation(com.springboot.cloud.common.core.annotation.MqProducerStore)")
    public void mqProducerStoreAspectAnnotationPointcut(){

    }

    @Around(value = "mqProducerStoreAspectAnnotationPointcut()")
    public Object processMqProducerStoreJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("processMqProducerStoreJoinPoint - 线程id={}", Thread.currentThread().getId());
        Object[] args = joinPoint.getArgs();

        if (args.length == 0) {
            throw new ServiceException(SystemErrorType.MESSAGE10050005);
        }
        MqMessageData mqMessageData = null;
        for(Object object : args){
            if (object instanceof MqMessageData){
                mqMessageData = (MqMessageData) object;
                break;
            }
        }
        MqProducerStore mqProducerStore = getAnnotation(joinPoint);
        int orderType = mqProducerStore.orderType().orderType();
        MqSendTypeEnum sendType = mqProducerStore.sendType();
        DelayLevelEnum delayLevel = mqProducerStore.delayLevel();
        mqMessageData.setOrderType(orderType);
        mqMessageData.setDelayLevel(delayLevel.delayLevel());
        if(sendType == MqSendTypeEnum.WAIT_CONFIRM){
            mqMessageService.saveWaitConfirmMessage(mqMessageData);
        }
        Object result = joinPoint.proceed();

        if (sendType == MqSendTypeEnum.SAVE_AND_SEND) {
//            mqMessageService.saveAndSendMessage(domain);
        } else if (sendType == MqSendTypeEnum.DIRECT_SEND) {
//            mqMessageService.directSendMessage(domain);
        } else {
            final MqMessageData finalDomain = mqMessageData;
            taskExecutor.execute(() -> mqMessageService.confirmAndSendMessage(finalDomain.getMessageKey()));
        }

        return result;
    }

    private MqProducerStore getAnnotation(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(MqProducerStore.class);
    }

}
