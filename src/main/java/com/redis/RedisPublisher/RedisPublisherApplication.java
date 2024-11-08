package com.redis.RedisPublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class RedisPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisPublisherApplication.class, args);
	}

}
