package com.dev.kangkrkr.config.redis;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

/**
 * 로컬환경에서는 편의성을 위해 내장 Redis를 사용한다.
 * 이외 실 개발 및 운영기에서는 외부 Redis를 사용하도록 변경할 예정이다.
 * @author 강승윤
 */
@Slf4j
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {

	@Value("${spring.redis.port}")
	private int redisPort;
	
	private RedisServer redisServer;
	
	@PostConstruct
	public void startRedis() throws IOException {
		redisServer = RedisServer.builder()
								 .port(redisPort)
								 .setting("maxheap 128M")
								 .setting("daemonize no")
								 .setting("appendonly no")
								 .build();
		redisServer.start();
	}
	
	@PreDestroy
	public void stopRedis() {
		Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
	}
}
