package com.heima.test;

import org.example.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 1. 建立连接
//        jedis = new Jedis("localhost", 6379);
        jedis = JedisConnectionFactory.getJedisPool().getResource();
        // 2. 认证密码
        jedis.auth("123456");
        // 3. 选择库
        jedis.select(0);
    }

    @Test
    void testString() {
        // 1. 存储字符串
        jedis.set("name", "虎哥");
        // 2. 获取字符串
        String name = jedis.get("name");
        System.out.println(name);
        // 3. 删除字符串
        jedis.del("name");
        // 4. 获取字符串
        name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    void testHash() {
        // 1. 存储hash
        jedis.hset("user", "name", "虎哥");
        jedis.hset("user", "age", "18");

        Map<String, String> map = jedis.hgetAll("user");
        System.out.println(map);

        // 2. 获取hash
        String name = jedis.hget("user", "name");
        System.out.println(name);
        // 3. 删除hash
        jedis.hdel("user", "name");
        // 4. 获取hash
        name = jedis.hget("user", "name");
        System.out.println(name);
    }

    @AfterEach
    void tearDown() {
        // 5. 关闭连接
        if (jedis != null) {
            jedis.close();
        }
    }
}
