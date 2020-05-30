package com.cjs.wymall.portal.service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/5/30
 */
public interface RedisService {

    /**
     * 保存数据
     * @param key
     * @param value
     * @param time
     */
    void set(String key,Object value,long time);

    /**
     * 保存数据
     * @param key
     * @param value
     */
    void set(String key,Object value);

    /**
     * 获取数据
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除数据
     * @param key
     * @return
     */
    Boolean delete(String key);

    /**
     * 批量删除数据
     * @param keys
     * @return
     */
    Long delete(List<String> keys);

    /**
     * 设置过期时间
     * @param key
     * @param time
     * @return
     */
    Boolean setExpire(String key,long time);

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 是否包含key
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 按delta递增
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key,long delta);

    /**
     * 按delta递减
     * @param key
     * @param delta
     * @return
     */
    Long decrement(String key,long delta);
}