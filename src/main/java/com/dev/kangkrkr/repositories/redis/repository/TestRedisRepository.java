package com.dev.kangkrkr.repositories.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.dev.kangkrkr.repositories.redis.vo.Test;

public interface TestRedisRepository extends CrudRepository<Test, Integer> {

}
