package com.selfspring.gimi.schedual;

import com.selfspring.gimi.annotation.RedisLockMust;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ckyang on 2019/6/29.
 */
@Component
@Configurable
@Slf4j
public class MyTask {
    @Scheduled(cron = "0/1 * * * * * ")
    public void execute() {
        String value = "key"+ (int)(Math.random() * 100);
        executeWithArg(value);
    }

    @RedisLockMust(value = "#id")
    private void executeWithArg(String id) {
        log.info("Task1>>" + id);
    }
}
