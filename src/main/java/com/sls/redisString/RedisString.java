package com.sls.redisString;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author sls
 **/
@Component
public class RedisString {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 新增一个字符串类型的值， key是建，value是值
     * <p>
     * set(K key, V value)
     */
    public void set() {
        // 存入永久数据
        stringRedisTemplate.opsForValue().set("test2", "1");
        // 也可以向redis里存入数据和设置缓存数据
        stringRedisTemplate.opsForValue().set("test1", "hello redis", 1000, TimeUnit.SECONDS);
    }

    /**
     * 批量插入， key值存在会覆盖原值
     * <p>
     * multiSet(Map<? extends K, ? extends V> map)
     */
    public void multiSet() {
        Map<String, String> map = new HashMap<>(16);
        map.put("testMultiSet1", "value0");
        map.put("testMultiSet2", "value2");
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 批量插入，如果里面的所有key都不存在，则全部插入， 返回true， 如果其中一个在redis中已存在，全部插入， 返回false
     * <p>
     * multiSetIfAbsent(Map<? extends K, ? extends V> map)
     */
    public void multiSetIfAbsent() {
        Map<String, String> map = new HashMap<>(16);
        map.put("testMultiSet4", "value1");
        map.put("testMultiSet3", "value3");
        Boolean absent = stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
        System.out.println(absent);
    }

    /**
     * 如果不存在则插入，返回true为插入成功，false失败
     * <p>
     * setIfAbsent(K key, V var2)
     */
    public void setIfAbsent() {
        Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent("test", "hello redis");
        System.out.println(absent);
    }

    /**
     * 获取值，key不存在返回null
     * <p>
     * get(Object key)
     */
    public void get() {
        System.out.println(stringRedisTemplate.opsForValue().get("testMultiSet1"));
    }

    /**
     * 批量获取，key不存在返回null
     * <p>
     * multiGet(Collection<K> keys)
     */
    public void multiGet() {
        List<String> list = stringRedisTemplate.opsForValue().multiGet(Arrays.asList("test", "test2"));
        assert list != null;
        System.out.println(list.toString());
    }

    /**
     * 获取指定字符串的长度
     * <p>
     * size(K key)
     */
    public void size() {
        Long size = stringRedisTemplate.opsForValue().size("test");
        System.out.println(size);
    }

    /**
     * 在原有的值基础上新增字符串到末尾
     * <p>
     * append(K key, String value)
     */
    public void append() {
        Integer append = stringRedisTemplate.opsForValue().append("test3", "database");
        System.out.println(append);
    }

    /**
     * 获取原来key建对应的值并重新赋新值
     * <p>
     * getAndSet(K key, V value)
     */
    public void getAndSet() {
        String set = stringRedisTemplate.opsForValue().getAndSet("test", "set test");
        System.out.println(set);
    }

    /**
     * 获取指定key的值进行减一，如果value不是Integer类型，会抛出异常，如果key不存在会创建一个，默认为value为0
     * <p>
     * decrement(k key)
     */
    public void decrement() {
        stringRedisTemplate.opsForValue().decrement("test2");
        stringRedisTemplate.opsForValue().decrement("test1");
    }

    /**
     * 获取指定的值进行加一，如果value不是Integer类型，会抛出异常，如果key不存在会创建一个，默认value为0
     * <p>
     * increment(K key)
     */
    public void increment() {
        stringRedisTemplate.opsForValue().increment("test2");
        stringRedisTemplate.opsForValue().increment("test1");
    }

    /**
     * 删除指定key，成功返回true，否则false
     * <p>
     * delete(K key)
     */
    public void delete() {
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete("test1");
        System.out.println(delete);
    }

    /**
     * 删除多个key，返回删除key的个数
     * <p>
     * delete(Collection<K> keys)
     */
    public void deleteMulti() {
        Long delete = stringRedisTemplate.opsForValue().getOperations().delete(Arrays.asList("test1", "test2"));
        System.out.println(delete);
    }
}
