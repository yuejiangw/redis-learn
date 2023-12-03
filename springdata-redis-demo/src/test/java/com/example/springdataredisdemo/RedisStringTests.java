package com.example.springdataredisdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@SpringBootTest
class RedisStringTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name:1", "虎哥");
        System.out.println(redisTemplate.opsForValue().get("name:1"));
    }

    @Test
    void testSaveUser() throws JsonProcessingException {
        User user = new User("虎哥", 21);
        // 手动序列化
        String json = MAPPER.writeValueAsString(user);
        // 写入数据
        redisTemplate.opsForValue().set("user:1", json);
        System.out.println(redisTemplate.opsForValue().get("user:1"));

        // 读取数据
        String jsonUser = redisTemplate.opsForValue().get("user:1");

        // 手动反序列化
        User user1 = MAPPER.readValue(jsonUser, User.class);
        System.out.println(user1);
    }

    @Test
    void testHash() {
        redisTemplate.opsForHash().put("user:2", "name", "虎哥");
        redisTemplate.opsForHash().put("user:2", "age", "21");

        redisTemplate.opsForHash().entries("user:2").forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        System.out.println(redisTemplate.opsForHash().get("user:2", "name"));
        System.out.println(redisTemplate.opsForHash().get("user:2", "age"));
    }
}
