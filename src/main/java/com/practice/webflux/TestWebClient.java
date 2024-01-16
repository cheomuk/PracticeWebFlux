package com.practice.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class TestWebClient {
    private final WebClient client = WebClient.create("http://localhost:8888");

    private Mono<ClientResponse> resultHi = client.get()
            .uri("/api/v1/hi")
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .bodyToMono(ClientResponse.class);
}
