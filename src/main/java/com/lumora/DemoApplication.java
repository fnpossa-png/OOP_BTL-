package com.lumora;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner testRedis(StringRedisTemplate redisTemplate) {
		return args -> {
			// Thử ghi dữ liệu lên Redis
			redisTemplate.opsForValue().set("test_key", "Hello from Spring Boot & Redis!");

			// Thử đọc lại dữ liệu từ Redis
			Object value = redisTemplate.opsForValue().get("test_key");
			System.out.println("====== REDIS TEST VALUE: " + value + " ======");
		};
	}

}