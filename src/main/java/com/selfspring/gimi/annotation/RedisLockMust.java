package com.selfspring.gimi.annotation;

import java.lang.annotation.*;

/**
 * Created by ckyang on 2019/6/29.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLockMust {
    String value() default "defaultRedisLockKey";
}
