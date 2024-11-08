package com.redis.RedisPublisher.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String topic, Object message) {
        redisTemplate.convertAndSend(topic, message);
    }
}