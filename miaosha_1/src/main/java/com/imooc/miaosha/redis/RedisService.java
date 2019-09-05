package com.imooc.miaosha.redis;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created By wangbo
 * 2019/9/4
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;


    public <T> T get(String key, Class<T> clazz) {

        Jedis jedis = null;

        T t = null;

        try{
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            t = stringToBean(str, clazz);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            returnToPool(jedis);
        }
        return t;
    }

    public <T> boolean set(String key, T value) {

        Jedis jedis = null;

        try{
            jedis = jedisPool.getResource();
            String str = beanToStr(value);
            if(str != null && str.length() > 0) {
                jedis.set(key, str);
            }
        }finally {
            returnToPool(jedis);
        }

        return true;
    }

    private <T> String beanToStr(T value) {
        if(value == null)
            return null;

        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return "" + value;
        }else if(clazz == String.class){
            return (String) value;
        }else if(clazz == long.class || clazz == Long.class) {
            return "" + value;
        }else{
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String str, Class<T> clazz) {

        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }

        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(str);
        }else{
            return (T)JSON.toJavaObject(JSON.parseObject(str), clazz);
        }

    }

    private void returnToPool(Jedis jedis) {
     if(jedis != null) {
         jedis.close();
     }
    }


}
