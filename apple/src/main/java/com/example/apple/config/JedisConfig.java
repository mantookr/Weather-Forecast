package com.example.apple.config;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class JedisConfig {
    public static JedisPool obj = null;
    public static JedisPool getJedisInstance() {
        if(null != null) {
            return obj;
        }
       return obj = new JedisPool(new JedisPoolConfig(), "localhost");
    }
}
