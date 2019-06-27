package com.sls.redisMap;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author sls
 **/
@Component
public class RedisHash {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 新增hashMap值
     *
     * put(H key, HK hashKey, HV value)
     */
    public void put() {
        stringRedisTemplate.opsForHash().put("hash","hash-key","hash-value");
        stringRedisTemplate.opsForHash().put("hash","hash-key2","hash-value2");
    }

    /**
     * 以map集合的形式添加键值对
     *
     * putAll(H key, Map<? extend HK, ? extend HV m>)
     */
    public void putAll() {
        Map<String, String> map = new HashMap<>(16);
        map.put("hash-key3","hash-value3");
        map.put("hash-key4","hash-value4");
        stringRedisTemplate.opsForHash().putAll("hash",map);
    }

    /**
     * 如果变量值存在，在变量中可以添加不存在的键值对，如果变量不存在，在新增一个变量，同时将键值对添加到该变量，添加成功返回true否则返回false
     *
     * putIfAbsent(H key, HK hashKey, HV value)
     */
    public void putIfAbsent() {
        Boolean absent = stringRedisTemplate.opsForHash().putIfAbsent("hash", "hash-key", "value1");
        Boolean absent1 = stringRedisTemplate.opsForHash().putIfAbsent("hash", "hash-key5", "value5");
        System.out.println(absent);
        System.out.println(absent1);
    }

    /**
     * 获取指定变量中的hashMap值
     *
     * value(H key)
     */
    public void values() {
        List<Object> values = stringRedisTemplate.opsForHash().values("hash2");
        System.out.println(values.toString());
    }

    /**
     * 获取变量中的键值对
     *
     * entries(H key)
     */
    public void entires() {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("hash");
        System.out.println(entries);
    }

    /**
     * 获取变量中的键
     *
     * keys(H key)
     */
    public void keys() {
        Set<Object> keys = stringRedisTemplate.opsForHash().keys("hash");
        System.out.println(keys);
    }

    /**
     * 获取变量的长度
     *
     * size(H key)
     *
     */
    public void siz() {
        Long size = stringRedisTemplate.opsForHash().size("hash");
        System.out.println(size);
    }

    /**
     * 使变量中的键已long值的大小进行自增长，值必须为Integer类型，否则异常
     *
     * increment(H key, HK hashKsy, long data)
     */
    public void increment() {
        Long increment = stringRedisTemplate.opsForHash().increment("hash", "hash-key2", 1);
        System.out.println(increment);
    }

    /**
     * 以集合的方式获取变量中的值
     *
     * multiGet(H key, Collection<HK> hashKeys)
     */
    public void multiGet() {
        List<Object> value = stringRedisTemplate.opsForHash().multiGet("hash", Arrays.asList("hash-key", "hash-key2"));
        System.out.println(value.toString());
    }

    /**
     * 匹配获取键值对，ScanOption，NONE为获取全部键值对，ScanOption.scanOptions().match("hash-key2").build
     * 匹配获取键位map1 的键值对，不能模糊匹配
     *
     * sacn(H key, ScanOptions options)
     */
    public void scan() {
        Cursor<Map.Entry<Object, Object>> scan = stringRedisTemplate.opsForHash().scan("hash", ScanOptions.NONE);
        while (scan.hasNext()) {
            Map.Entry<Object,Object> next = scan.next();
            System.out.println(next.getKey() + "---->" + next.getValue());
        }
    }

    /**
     * 删除变量中的键值对，可以传入多个参数，删除多个键值对，返回删除成功的个数
     *
     * delete(H key, Ohject ... hashKeys)
     */
    public void delete() {
        Long delete = stringRedisTemplate.opsForHash().delete("hash", "hash-key", "hash-key1");
        System.out.println(delete);
    }


}
