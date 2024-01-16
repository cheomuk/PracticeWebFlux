package com.practice.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TestRouter {

    @Bean
    public RouterFunction<ServerResponse> route(TestHandler testHandler){
        return RouterFunctions.route()
                .GET("/api/v1/hi", testHandler::hi)
                .GET("/api/v1/test/{id}", testHandler::test)
                .build();
    }
}
