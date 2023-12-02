package com.example.springdataredisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringdataRedisDemoApplicationTests {

    private final StringRedisTemplate stringRedisTemplate;
    @Autowired
    SpringdataRedisDemoApplicationTests(final StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "zhangsan");
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
    }

}
