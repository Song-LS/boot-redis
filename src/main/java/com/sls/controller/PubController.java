package com.sls.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sls
 **/
@RestController
public class PubController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static AtomicInteger count = new AtomicInteger();

    @PostMapping("/message")
    public void sendMessage() {
        int i = count.incrementAndGet();
        String test2 = stringRedisTemplate.opsForValue().get("test2");
        stringRedisTemplate.convertAndSend("topic", "消息" + i);
        stringRedisTemplate.convertAndSend("topic","消息3-->" + test2);
    }

}
