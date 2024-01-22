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
                .POST("/api/v1/characters", testHandler::saveCharacter)
                .GET("/api/v1/characters/{id}", testHandler::getCharacterById)
                .GET("/api/v1/characters", testHandler::getAllCharacters)
                .build();
    }
}
