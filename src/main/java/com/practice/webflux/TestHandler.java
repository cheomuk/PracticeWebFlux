package com.practice.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TestHandler {

    public Mono<ServerResponse> hi(ServerRequest serverRequest){
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hi, Spring!"));
    }

    public Mono<ServerResponse> test(ServerRequest serverRequest){
        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        WebClient.create()
                .get()
                .uri("http://localhost:8888/api/v1/test/{id}", id)
                .retrieve()
                .bodyToMono(ClientResponse.class);

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Test : " + id));
    }
}
