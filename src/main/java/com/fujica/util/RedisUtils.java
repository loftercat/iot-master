package com.fujica.util;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate){
        RedisUtils.redisTemplate = redisTemplate;
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        if (factory != null) {
            RedisConnection conn = RedisConnectionUtils.getConnection(factory);
            log.info("Redis服务器信息：{}", conn.info());
        }
    }

    public static void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    public static void set(String key, Object value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key,value,timeout,unit);
    }

    public static void setExpireSecond(String key, Object value, long timeout){
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);
    }

    public static <V> void putListAllRight(String key, Collection<V> values){
        if(CollectionUtils.isEmpty(values)){
            log.warn("{}没有数据可放入Redis",key);
        }else{
            redisTemplate.opsForList().rightPushAll(key,values);
        }
    }

    public static <T> T getListAll(String key,Class<?> T){
        return (T)redisTemplate.opsForList().range(key,0,-1);
    }

    public static <T> T get(String key,Class<?> T){
        return (T)redisTemplate
                .opsForValue().get(key);
    }

    public static String get(String key){
        return (String) redisTemplate
                .opsForValue().get(key);
    }

    public static Long decr(String key){
        return redisTemplate
                .opsForValue().decrement(key);
    }

    public static Long decr(String key,long delta){
        return redisTemplate
                .opsForValue().decrement(key,delta);
    }

    public static Long incr(String key){
        return redisTemplate
                .opsForValue().increment(key);
    }

    public static Long incr(String key,long delta){
        return redisTemplate
                .opsForValue().increment(key,delta);
    }

    public static boolean expire(String key,long timeout,TimeUnit unit){
        return redisTemplate.expire(key,timeout, unit);
    }

    /**
     * 获取key的过期时间
     * @param key key
     * @return 过期时间
     */
    public static long getExpireTimeSeconds(String key) {
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (expire == null) {
            return 0;
        }
        return expire;
    }

    public static boolean delete(String key){
        return redisTemplate.delete(key);
    }

    public static boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 发布channel信息
     * @param channel
     * @param message
     */
    public static void publish(String channel,Object message){
        redisTemplate.convertAndSend(channel,message);
    }

    /**
     * 设置hashmap
     * @param key
     * @param maps
     */
    public static <T> void hPutAll(String key, Map<String, T> maps) {
        redisTemplate.opsForHash().putAll(key, maps);
    }

    /**
     *
     * @param key
     * @param hashKey
     * @param hashValue
     */
    public static <T> void hPutValue(String key, String hashKey, T hashValue) {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    //删除haskkey
    public static void hDelete(String key, String hashKey){
        redisTemplate.opsForHash().delete(key,hashKey);
    }


    /**
     * 获取哈希值
     * @param key
     * @return
     */
    public static <T> T hGetValue(String key, String hashKey, Class<T> clazz){
        return (T)redisTemplate.opsForHash().get(key,hashKey);
    }

    /**
     *  扫描符合pattern的key
     * @param pattern redis key前缀
     * @return
     */
    public static int scan(String pattern){
        //如果pattern的最后一位不是*， 则加上*
        if (!pattern.endsWith("*")){
            pattern = pattern + "*";
        }
        Set<String> keys = redisTemplate.keys(pattern);
        if (CollectionUtil.isEmpty(keys)){
            return 0;
        }
        return keys.size();
    }

    /**
     * 删除所有符合pattern的key
     * @param pattern redis key前缀
     */
    public static void deletePattenKeys(String pattern) {
        //如果pattern的最后一位不是*， 则加上*
        if (!pattern.endsWith("*")){
            pattern = pattern + "*";
        }
        Set<String> keys = redisTemplate.keys(pattern);
        if (CollectionUtil.isEmpty(keys)){
            return;
        }
        redisTemplate.delete(keys);
    }

    public static Map<Object, Object> getHashAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public static <T> void zPut(String key, String zKey, double score, T value) {
        redisTemplate.opsForZSet().add(key, zKey, score);
    }
}