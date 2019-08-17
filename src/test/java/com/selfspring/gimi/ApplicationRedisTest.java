package com.selfspring.gimi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ckyang on 2019/6/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TimmirApplication.class)
@EnableAutoConfiguration
public class ApplicationRedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception{
        //保存字符串
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
    }
}