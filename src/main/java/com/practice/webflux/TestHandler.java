package com.practice.webflux;

import com.practice.webflux.dto.RequestNicknameDto;
import com.practice.webflux.dto.RequestOcidDto;
import com.practice.webflux.entity.TestEntity;
import com.practice.webflux.repository.TestRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TestHandler {

    private final TestRepository testRepository;
    private final TestWebClient webClient;

    public TestHandler(TestRepository testRepository, TestWebClient webClient) {
        this.testRepository = testRepository;
        this.webClient = webClient;
    }

    public Mono<ServerResponse> saveCharacter(ServerRequest request) {
        Mono<Object> characterMono = request.bodyToMono(RequestNicknameDto.class)
                .flatMap(dto -> Mono.justOrEmpty(dto.getCharacterName()));
        Mono<Object> ocid = webClient.getCharacterId(String.valueOf(characterMono))
                .flatMap(dto -> Mono.justOrEmpty(dto.getOcid()));
        Mono<TestEntity> characterValue = webClient.getCharacterInfo(String.valueOf(ocid));
        // ocid 가 MonoFlatMap 그대로 String 화 되어서 입력되는 문제 발생.

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(characterValue.flatMap(testRepository::save), TestEntity.class);
    }

    public Mono<ServerResponse> getCharacterById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<TestEntity> characterMono = testRepository.findById(id);
        return characterMono.flatMap(character -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(character))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllCharacters(ServerRequest request) {
        Flux<TestEntity> characterFlux = testRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(characterFlux, TestEntity.class);
    }
}
