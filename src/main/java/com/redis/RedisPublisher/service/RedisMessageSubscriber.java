package com.redis.RedisPublisher.service;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisMessageSubscriber {

    public void onMessageReceived(String message) {
        System.out.println("Received message from Redis queue: " + message);
        log.info("Message received from Redis queue: " + message);
    }
}
