package com.cjs.wymall.portal.service.impl;

import com.cjs.wymall.portal.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: 封装redis操作Service实现类
 * @author: cuijunsheng
 * @date: 2020/5/30
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long delete(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public Boolean setExpire(String key, long time) {
        return redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);
    }

    @Override
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key,delta);
    }
}