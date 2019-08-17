package com.selfspring.gimi.aspect;

import com.selfspring.gimi.annotation.RedisLockMust;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ckyang on 2019/6/29.
 */
@Aspect
@Component
@Slf4j
public class RedisLockAspect {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Around("@annotation(must)")
    public void mustLock(ProceedingJoinPoint pjp,RedisLockMust must) throws Throwable {
        try {
            long curr = System.currentTimeMillis();
            if(curr % 2 == 0){
                redisTemplate.opsForValue().set(must.value(),String.valueOf(curr));
                pjp.proceed();
            }else{
                log.info("当前不处理:" + redisTemplate.opsForValue().get(must.value()));
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

}
