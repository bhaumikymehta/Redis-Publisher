package com.redis.RedisPublisher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.redis.RedisPublisher.service.RedisMessageSubscriber;

@Configuration
public class RedisConfig {

    public static final String QUEUE_NAME = "myQueue";

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use Jackson 2 for serializing/deserializing objects to JSON
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setKeySerializer(new GenericToStringSerializer<>(Object.class));
        template.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));

        return template;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        // Create a MessageListenerAdapter to handle incoming messages
        // You can create a separate class that implements MessageListener
        // or provide a bean name and method name here.
        return new MessageListenerAdapter(new RedisMessageSubscriber(), "onMessageReceived");
    }

    @Bean
    RedisMessageListenerContainer containerConfig(RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer containerConfig = new RedisMessageListenerContainer();
        containerConfig.setConnectionFactory(connectionFactory);
        // Subscribe the listener to the specified queue name
        containerConfig.addMessageListener(listenerAdapter, new ChannelTopic(QUEUE_NAME));
        return containerConfig;
    }
}