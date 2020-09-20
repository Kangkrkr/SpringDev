package com.dev.kangkrkr.repositories.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.dev.kangkrkr.repositories.redis.vo.Point;

public interface PointRedisRepository extends CrudRepository<Point, String>{

}
