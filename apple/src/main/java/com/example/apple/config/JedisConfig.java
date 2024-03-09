package com.example.apple.config;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class JedisConfig {
    private static volatile JedisPool obj = null;
    public static JedisPool getJedisInstance() {
        if(null == obj) {
            synchronized(JedisConfig.class) {
                if(null == obj) {
                    obj = new JedisPool(new JedisPoolConfig(), "localhost");
                }
            }
            
        }
       return obj; 
    }
}
