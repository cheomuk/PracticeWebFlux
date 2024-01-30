package com.practice.webflux.controller;

import com.practice.webflux.entity.TestEntity;
import com.practice.webflux.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/users")
    Flux<TestEntity> getUserRank() {
        return testService.getRankUser();
    }
}
