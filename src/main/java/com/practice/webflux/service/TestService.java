package com.practice.webflux.service;

import com.practice.webflux.entity.TestEntity;
import com.practice.webflux.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public Flux<TestEntity> getRankUser() {
        return testRepository.findAll();
    }
}
