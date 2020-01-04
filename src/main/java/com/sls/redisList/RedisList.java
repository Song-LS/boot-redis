package com.sls.redisList;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
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
     * <p>
     * leftPush(K key, V value)
     */
    public void leftPush() {
        Long aLong = stringRedisTemplate.opsForList().leftPush("list", "a");
        System.out.println(aLong);
    }

    /**
     * 向左边批量添加参数元素， 如果key不存在会新建，添加成功返回添加后的总个数
     * <p>
     * leftPushAll(K key, V.. values)
     */
    public void leftPushAll() {
        Long pushAll = stringRedisTemplate.opsForList().leftPushAll("list", "e", "f", "g");
        System.out.println(pushAll);
    }

    /**
     * 在变量右边添加元素，如果key不存在会新建，添加成功返回添加后的总个数
     * <p>
     * rightPush(K key, V value)
     */
    public void rightPush() {
        Long rightPush = stringRedisTemplate.opsForList().rightPush("list2", "a");
        System.out.println(rightPush);
    }

    /**
     * 向右边批量添加元素， 如果key不存则会新建，添加成功返回添加后的总个数
     * <p>
     * rightPushAll(K key, V ...values)
     */
    public void rightPushAll() {
        Long rightPushAll = stringRedisTemplate.opsForList().rightPushAll("list2", "e", "f", "g");
        System.out.println(rightPushAll);
    }

    /**
     * 如果存在集合则添加元素
     * <p>
     * leftPushIfPresent(K key, V value)
     */
    public void leftPushIfPresent() {
        Long aLong = stringRedisTemplate.opsForList().leftPushIfPresent("list", "h");
        System.out.println(aLong);
    }

    /**
     * 向已存在的集合中添加元素，返回集合总元素个数
     * <p>
     * rigthPushIfPresent(k key, V value)
     */
    public void rightPushIfPresent() {
        Long aLong = stringRedisTemplate.opsForList().rightPushIfPresent("list2", "e");
        System.out.println(aLong);
    }

    /**
     * 获取集合长度
     * <p>
     * size(K key)
     */
    public void size() {
        Long size = stringRedisTemplate.opsForList().size("list2");
        System.out.println(size);
    }

    /**
     * 移除集合中的左边第一个元素，返回删除的元素，如果元素为空，该集合会自动删除
     * <p>
     * leftPop(K key)
     */
    public void leftPop() {
        String pop = stringRedisTemplate.opsForList().leftPop("list");
        System.out.println(pop);
    }

    /**
     * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出
     * <p>
     * leftPop(K key, long timeout, TimeUnit unit)
     */
    public void leftPopWait() {
        String pop = stringRedisTemplate.opsForList().leftPop("list2", 10, TimeUnit.SECONDS);
        System.out.println(pop);
    }

    /**
     * 移除集合中右边的元素，返回删除的元素，如果元素为空，该集合会自动删除
     * <p>
     * rigthPop(K key)
     */
    public void rightPop() {
        String pop = stringRedisTemplate.opsForList().rightPop("list");
        System.out.println(pop);
    }

    /**
     * 移除集合中右边的元素在等待的时间里， 如果超过等待的时间仍然没有元素则退出
     * <p>
     * rightPop(K key, long timeout, TimeUnit unit)
     */
    public void rightPopWait() {
        stringRedisTemplate.opsForList().rightPop("list2", 10, TimeUnit.SECONDS);
    }

    /**
     * 移除第一个集合右边的一个元素，插入第二个集合左边插入这个元素
     * <p>
     * rightPopAndLeftPush(K sourceKet, k destinationKey)
     */
    public void rightPopAndLeftPush() {
        stringRedisTemplate.opsForList().rightPopAndLeftPush("list2", "list3");
    }

    /**
     * 在集合的指定位置插入元素，如果指定位置已有元素，则覆盖，没有则新增，超过集合下标+n则会报错
     * <p>
     * set(K key, long index, V value)
     */
    public void set() {
        stringRedisTemplate.opsForList().set("list2", 2, "w");
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件，count>0：删除等于从左到右移动的值
     * count<0：删除等于从右到左移动的值的第一个元素；
     * count=0：删除等于value的所有元素
     * <p>
     * remove(K key, long count, Object value)
     */
    public void remove() {
        Long remove = stringRedisTemplate.opsForList().remove("list2", 2, "w");
        System.out.println(remove);
    }

    /**
     * 截取集合元素长度， 保留长度内的数据
     * <p>
     * trim(K key, long start, long end)
     */
    public void trim() {
        stringRedisTemplate.opsForList().trim("list2", 0, 3);
    }

    /**
     * 获取集合指定位置的值
     * <p>
     * index(K key, long index)
     */
    public void index() {
        String listValue = stringRedisTemplate.opsForList().index("list2", 3);
        System.out.println(listValue);
    }

    /**
     * 获取指定区间的值
     * <p>
     * range(K key, long start, long end)
     */
    public void range() {
        List<String> list = stringRedisTemplate.opsForList().range("list", 0, -1);
        System.out.println(list);
    }

    /**
     * 删除指定集合，返回true，删除成功
     * <p>
     * delete(K key)
     */
    public void delete() {
        Boolean delete = stringRedisTemplate.opsForList().getOperations().delete("list2");
        System.out.println(delete);
    }
}
