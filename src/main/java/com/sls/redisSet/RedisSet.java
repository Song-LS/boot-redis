package com.sls.redisSet;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author sls
 **/
@Component
public class RedisSet {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 向变量中批量添加值，返回添加的数量
     * <p>
     * add(K key, V values)
     */
    public void add() {
        Long add = stringRedisTemplate.opsForSet().add("set", "a", "b", "c");
        System.out.println(add);
    }

    /**
     * 获取变量的值
     * <p>
     * members(K key)
     */
    public void members() {
        Set<String> set = stringRedisTemplate.opsForSet().members("set");
        System.out.println(set);
    }

    /**
     * 获取变量中值的长度
     * <p>
     * size(K key)
     */
    public void size() {
        Long size = stringRedisTemplate.opsForSet().size("set");
        System.out.println(size);
    }

    /**
     * 随机获取变量中指定个数的元素
     * <p>
     * randomMembers(K key, long count)
     */
    public void randomMembers() {
        List<String> members = stringRedisTemplate.opsForSet().randomMembers("set", 2);
        System.out.println(members);
    }

    /**
     * 检查给定的元素是否在变量中，true为存在
     * <p>
     * isMember(K key, Object value)
     */
    public void isMember() {
        Boolean member = stringRedisTemplate.opsForSet().isMember("set", "b");
        System.out.println(member);
    }

    /**
     * 转义变量的元素值到另一个变量中
     * <p>
     * move(K key, V value, K targetKey)
     */
    public void move() {
        Boolean move = stringRedisTemplate.opsForSet().move("set", "b", "set2");
        System.out.println(move);
    }

    /**
     * 弹出变量中的元素，当元素全部弹完，变量也会删除
     * <p>
     * pop(K key)
     */
    public void pop() {
        String pop = stringRedisTemplate.opsForSet().pop("set");
        System.out.println(pop);
    }

    /**
     * 批量删除变量中的元素，返回删除的数量
     * <p>
     * remove(K key, V ... values)
     */
    public void remove() {
        Long remove = stringRedisTemplate.opsForSet().remove("set2", "b");
        System.out.println(remove);
    }

    /**
     * 匹配获取键值对，ScanOptions.NONE为获取全部键值对；
     * ScanOptions.scanOptions().match("C").build()匹配获取键位map1的键值对,不能模糊匹配。
     * <p>
     * scan(K key, ScanOptions options)
     */
    public void scan() {
        Cursor<String> scan = stringRedisTemplate.opsForSet().scan("set", ScanOptions.NONE);
        while (scan.hasNext()) {
            String next = scan.next();
            System.out.println(next);
        }
    }

    /**
     * 通过集合求差值
     * <p>
     * difference(K key, K otherKey)
     */
    public void difference() {
        Set<String> difference = stringRedisTemplate.opsForSet().difference("set", "set2");
        System.out.println(difference);
    }

    /**
     * 将求出了的差值元素保存
     * <p>
     * differenceAndStore(K key, K otherKey, K targetKey)
     */
    public void differenceAndStore() {
        Long differenceAndStore = stringRedisTemplate.opsForSet().differenceAndStore("set", "set2", "set3");
        System.out.println(differenceAndStore);
    }

    /**
     * 获取去重的随机元素
     * <p>
     * distinctRandomMembers(K key, long count)
     */
    public void distinctRandomMember() {
        Set<String> set = stringRedisTemplate.opsForSet().distinctRandomMembers("set", 2);
        System.out.println(set);
    }

    /**
     * 获取两个变量中的交集
     * <p>
     * intersect(K key, K otherkey)
     */
    public void intersect() {
        Set<String> intersect = stringRedisTemplate.opsForSet().intersect("set", "set2");
        System.out.println(intersect);
    }

    /**
     * 获取两个变量交集后保存到最后一个变量上
     * <p>
     * intersectAndStore(K key, K otherKey, K targetKey)
     */
    public void intersectAndStore() {
        Long aLong = stringRedisTemplate.opsForSet().intersectAndStore("set", "set2", "set3");
        System.out.println(aLong);
    }

    /**
     * 获取两个变量的合集
     * <p>
     * union(K key, K otherKey)
     */
    public void union() {
        Set<String> union = stringRedisTemplate.opsForSet().union("set", "set2");
        System.out.println(union);
    }

    /**
     * 获取两个变量合集后保存到另一个变量中
     * <p>
     * unionAndStore(K key, K otherKey, K targetKey)
     */
    public void unionAndStore() {
        Long aLong = stringRedisTemplate.opsForSet().unionAndStore("set", "set2", "set3");
        System.out.println(aLong);
    }
}
