package com.jxcia.pt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     */
    public void expire(String key, Long time) {
        if (time < 0) {
            throw new RuntimeException("time必须大于等于0");
        }
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取过期时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key删除
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }

    /**
     * String缓存获取
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * String缓存存储
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * String缓存存储限时
     */
    public void set(String key, Object value, Long time) {
        if (time < 0) {
            throw new RuntimeException("time必须大于等于0");
        }
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * Map数据获取
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * Map数据存储
     */
    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * Map数据存储 expire
     */
    public void hset(String key, String item, Object value, Long time) {
        redisTemplate.opsForHash().put(key, item, value);
        this.expire(key, time);
    }

    /**
     * Map数据删除 key item
     */
    public void hdel(String key, String item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * Map数据获取多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Map数据设置 key map
     */
    public void hmset(String key, Map<String,Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * Map数据设置 key map expire
     */
    public void hmset(String key, Map<String,Object> map, Long time) {
        if (time < 0) {
            throw new RuntimeException("time必须大于等于0");
        }
        redisTemplate.opsForHash().putAll(key, map);
        this.expire(key, time);
    }

    /**
     * Map数据 has key
     */
    public Boolean hhasItem(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

}
