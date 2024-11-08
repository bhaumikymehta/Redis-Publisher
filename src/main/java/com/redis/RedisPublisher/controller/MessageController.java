package com.redis.RedisPublisher.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.RedisPublisher.service.RedisMessagePublisher;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class MessageController {

    private final RedisMessagePublisher redisMessagePublisher;

    public MessageController(RedisMessagePublisher redisMessagePublisher) {
        this.redisMessagePublisher = redisMessagePublisher;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String jsonMessage) {

        redisMessagePublisher.publish("myTopic", jsonMessage);
        log.info("Message published successfully");
        return new ResponseEntity<>("Message published successfully", HttpStatus.OK);
    }
}
