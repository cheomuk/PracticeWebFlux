package com.practice.webflux.repository;

import com.practice.webflux.entity.TestEntity;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@EnableR2dbcRepositories
public interface TestRepository extends ReactiveCrudRepository<TestEntity, Long> {

}
