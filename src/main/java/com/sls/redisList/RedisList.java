package com.sls.redisList;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author sls
 **/
@Component
public class RedisList {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 在变量左边添加元素值，如果key不存在会新建，添加成功返回添加后的总个数
     *
     * leftPush(K key, V value)
     */
    public void leftPush() {
        Long aLong = stringRedisTemplate.opsForList().leftPush("list", "a");
        System.out.println(aLong);
    }

    /**
     * 向左边批量添加参数元素， 如果key不存在会新建，添加成功返回添加后的总个数
     *
     * leftPushAll(K key, V.. values)
     */
    public void leftPushAll() {
        Long pushAll = stringRedisTemplate.opsForList().leftPushAll("list", "e", "f", "g");
        System.out.println(pushAll);
    }

    /**
     * 在变量右边添加元素，如果key不存在会新建，添加成功返回添加后的总个数
     *
     * rightPush(K key, V value)
     */
    public void rightPush() {
        Long rightPush = stringRedisTemplate.opsForList().rightPush("list2", "a");
        System.out.println(rightPush);
    }

    /**
     * 向右边批量添加元素， 如果key不存则会新建，添加成功返回添加后的总个数
     *
     * rightPushAll(K key, V ...values)
     */
    public void rightPushAll() {
        Long rightPushAll = stringRedisTemplate.opsForList().rightPushAll("list2", "e", "f", "g");
        System.out.println(rightPushAll);
    }

    /**
     * 如果存在集合则添加元素
     *
     * leftPushIfPresent(K key, V value)
     */
    public void leftPushIfPresent() {
        Long aLong = stringRedisTemplate.opsForList().leftPushIfPresent("list", "h");
        System.out.println(aLong);
    }

    /**
     * 向已存在的集合中添加元素，返回集合总元素个数
     *
     * rigthPushIfPresent(k key, V value)
     */
    public void rightPushIfPresent() {
        Long aLong = stringRedisTemplate.opsForList().rightPushIfPresent("list2", "e");
        System.out.println(aLong);
    }

    /**
     * 获取集合长度
     *
     * size(K key)
     */
    public void size() {
        Long size = stringRedisTemplate.opsForList().size("list2");
        System.out.println(size);
    }

    /**
     * 移除集合中的左边第一个元素，返回删除的元素，如果元素为空，该集合会自动删除
     *
     * leftPop(K key)
     */
    public void leftPop() {
        String pop = stringRedisTemplate.opsForList().leftPop("list");
        System.out.println(pop);
    }

    /**
     * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出
     *
     * leftPop(K key, long timeout, TimeUnit unit)
     */
    public void leftPopWait() {
        String pop = stringRedisTemplate.opsForList().leftPop("list2", 10, TimeUnit.SECONDS);
        System.out.println(pop);
    }

    /**
     * 移除集合中右边的元素，返回删除的元素，如果元素为空，该集合会自动删除
     *
     * rigthPop(K key)
     */
    public void rightPop() {
        String pop = stringRedisTemplate.opsForList().rightPop("list");
        System.out.println(pop);
    }


}
