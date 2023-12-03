package com.example.springdataredisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringdataRedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name", "虎哥");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    void testSaveUser() {
        User user = new User("虎哥", 21);
        // 写入数据
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));

        // 读取数据
        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1);
    }
}
