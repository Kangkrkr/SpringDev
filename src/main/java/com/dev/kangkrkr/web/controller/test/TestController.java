package com.dev.kangkrkr.web.controller.test;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.kangkrkr.repositories.redis.repository.TestRedisRepository;
import com.dev.kangkrkr.repositories.redis.vo.Test;

@RequestMapping("/test")
@RestController
public class TestController {

	@Autowired
	private TestRedisRepository testRedisReposytory;
	
	
	@GetMapping("/save/{id}")
	public Test saveTest(@PathVariable("id") int id) {
		Test toSave = Test.builder()
						  .id(id)
						  .value(LocalDateTime.now().toString())
						  .build();
		
		Test saved = testRedisReposytory.save(toSave);
		
		return saved;
	}
	
}
