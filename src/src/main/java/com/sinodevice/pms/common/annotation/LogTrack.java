package com.sinodevice.pms.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>
 * 日志跟踪注解
 * </p>
 *
 * @author jobob
 * @since 2018-10-07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTrack {

    /**
     * 描述
     */
    String value();

}
