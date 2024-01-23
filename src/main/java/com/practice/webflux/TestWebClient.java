package com.practice.webflux;

import com.practice.webflux.dto.RequestOcidDto;
import com.practice.webflux.entity.TestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class TestWebClient {

    @Value("${apiKey}")
    private String apiKey;

    private final WebClient webClient;

    public TestWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://open.api.nexon.com").build();
    }

    public Mono<RequestOcidDto> getCharacterId(String characterName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/maplestory/v1/id")
                        .queryParam("character_name", characterName)
                        .build())
                .header("x-nxopen-api-key", apiKey)
                .retrieve()
                .bodyToMono(RequestOcidDto.class);
    }

    public Mono<TestEntity> getCharacterInfo(String ocid) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/maplestory/v1/character/basic")
                        .queryParam("ocid", ocid)
                        .queryParam("date", LocalDate.now().minusDays(1).toString())
                        .build())
                .header("x-nxopen-api-key", apiKey)
                .retrieve()
                .bodyToMono(TestEntity.class);
    }
}
