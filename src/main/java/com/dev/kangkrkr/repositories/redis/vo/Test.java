package com.dev.kangkrkr.repositories.redis.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@RedisHash("test")
public class Test {

	@Id
	private int id;
	private String value;
	
	@Builder
	public Test(int id, String value) {
		this.id = id;
		this.value = value;
	}
}
