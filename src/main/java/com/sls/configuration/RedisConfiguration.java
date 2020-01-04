package com.sls.configuration;

import com.sls.receiver.Receiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author sls
 **/
@Configuration
public class RedisConfiguration {

    /**
     * 消息适配器
     * <p>
     * 绑定消息监听者和接收监听的方法,必须要注入这个监听器，不然会报错
     *
     * @return MessageListenerAdapter
     */
    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new Receiver(), "messageReceiver");
    }

    /**
     * 定义消息监听者容器
     *
     * @param redisConnectionFactory 连接工厂
     * @param messageListenerAdapter 消息处理器
     * @return RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("topic"));
        return redisMessageListenerContainer;
    }
}
