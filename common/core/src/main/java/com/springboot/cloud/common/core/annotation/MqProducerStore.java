package com.springboot.cloud.common.core.annotation;


import com.springboot.cloud.common.core.message.entity.enums.DelayLevelEnum;
import com.springboot.cloud.common.core.message.entity.enums.MqOrderTypeEnum;
import com.springboot.cloud.common.core.message.entity.enums.MqSendTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MqProducerStore {

    MqSendTypeEnum sendType() default MqSendTypeEnum.WAIT_CONFIRM;

    MqOrderTypeEnum orderType() default MqOrderTypeEnum.ORDER;

    DelayLevelEnum delayLevel() default DelayLevelEnum.ZERO;

}
