package org.example;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8); // 最大连接数
        jedisPoolConfig.setMaxIdle(8); // 最大空闲连接数
        jedisPoolConfig.setMinIdle(0); // 最小空闲连接数
        jedisPoolConfig.setMaxWaitMillis(10000); // 最大等待时间

        // 创建连接池对象
        jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379, 1000, "123456");
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }
}