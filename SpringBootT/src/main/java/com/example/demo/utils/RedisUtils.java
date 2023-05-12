package com.example.demo.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * opsForValue
 * opsForHash
 * opsForSet
 * opsForList
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public Set<String> keys(String keys){
        return redisTemplate.keys(keys);
    }

    /**
     * 设置指定key过期时间
     */
    public void expire(String key,long time){
        if(time <= 0){
            return;
        }
        redisTemplate.expire(key,time, TimeUnit.SECONDS);
    }

    /**
     * 根据key获取过期时间
     */
    public Long getKeyExpireTime(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 是否存在key
     */
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key对应缓存
     */
    public boolean del(String... keys){
        if(null == keys || keys.length == 0){
            throw new RuntimeException("key is null");
        }
        if(keys.length == 1){
            redisTemplate.delete(keys[0]);
        }else{
            redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(keys));
        }
        return true;
    }

    /**
     * 缓存获取
     */
    public Object get(String key){
        if(null == key){
            throw new RuntimeException("key is null");
        }
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key,Object value){
        set(key,value,0);
    }

    public void set(String key,Object value,long time){
        if(null == key || null == value){
            throw new RuntimeException("key or value is null");
        }
        redisTemplate.opsForValue().set(key, value);
        expire(key,time);
    }

    /**
     * 存储缓存，如果key存在缓存中则不存储，否则存储
     * return:如果key不存在并且缓存成功，则返回true，否则返回false
     */
    public Boolean setIfAbsent(String key,String value){
        return setIfAbsent(key,value);
    }

    public Boolean setIfAbsent(String key,String value,long time){
        if(null == key || null == value){
            throw new RuntimeException("key or value is null");
        }

        if(time <= 0){
            return redisTemplate.opsForValue().setIfAbsent(key,value);
        }
        return redisTemplate.opsForValue().setIfAbsent(key,value,time,TimeUnit.SECONDS);
    }

    /**
     * 递增
     * @param key
     * @param delta
     * @return 返回key对应value递增之后的值
     */
    public Long incr(String key,long delta){
        if(delta <= 0){
            throw new RuntimeException("delta less than 0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public Long decr(String key,long delta){
        if(delta <= 0){
            throw new RuntimeException("delta less than 0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    /**
     * redis中给指定key存储对应Map
     * @param key
     * @param map
     */
    public void hmput(String key, Map<String,Object> map){
        hmput(key,map,0);
    }

    public void hmput(String key, Map<String,Object> map,long time){
        if(null == key || null == map){
            throw new RuntimeException("key or map is null");
        }

        redisTemplate.opsForHash().putAll(key,map);
        expire(key,time);
    }

    /**
     * 获取redis中指定key存储的map
     * @param key
     * @return
     */
    public Map<Object,Object> hmget(String key){
        if(null == key){
            throw new RuntimeException("key is null");
        }
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取指定Redis哈希中给定hashKey值
     * @param key
     * @param hashKey
     * @return
     */
    public Object hget(String key,String hashKey){
        if(null == key || null == hashKey){
            throw new RuntimeException("key or hashKey is null");
        }
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    /**
     * 设置指定key值下map中对应key值value
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmset(String key,String hashKey,Object value){
        hmset(key,hashKey,value,0);
    }

    public void hmset(String key,String hashKey,Object value,long time){
        if(null == key || null == hashKey || value == null){
            throw new RuntimeException("key or hashKey or value is null");
        }
        redisTemplate.opsForHash().put(key,hashKey,value);
        expire(key,time);
    }

    /**
     * 删除指定key对应map下key列表元素
     * @param key
     * @param hashKeys
     * @return 被删除元素数量
     */
    public Long hmdel(String key,Object... hashKeys){
        if(null == key || null == hashKeys || 0 == hashKeys.length){
            throw new RuntimeException("key or items is null");
        }
        return redisTemplate.opsForHash().delete(key,hashKeys);
    }

    /**
     * 判断key对应map中是否有键值
     * @param key
     * @param hashKey
     * @return
     */
    public Boolean hHasHashKey(String key,String hashKey){
        if(null == key || null == hashKey){
            throw new RuntimeException("key or hashKey is null");
        }
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }

    /**
     * @param key
     * @param hashKey
     * @param incr
     * @return 返回递增后的值
     */
    public Double hIncr(String key,String hashKey,double incr){
        return redisTemplate.opsForHash().increment(key,hashKey,incr);
    }

    public Double hDecr(String key,String hashKey,double decr){
        return hIncr(key,hashKey,-decr);
    }

    public void sset(String key,Object... values){
        redisTemplate.opsForSet().add(key,values);
    }

}
