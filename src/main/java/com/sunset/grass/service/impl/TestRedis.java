package com.sunset.grass.service.impl;

import com.sunset.grass.configuration.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

public class TestRedis {
    @Autowired
    private RedisUtil redisUtil;

    void testRedis(){
        Jedis jedis=redisUtil.getJedis();
    }
}
