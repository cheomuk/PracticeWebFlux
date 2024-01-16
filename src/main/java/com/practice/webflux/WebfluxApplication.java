package com.practice.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WebfluxApplication {

    @GetMapping("/api/v1/hello")
    public String hello() { return "Hello WebFlux!!!"; }

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
        TestWebClient wc = new TestWebClient();

        System.out.println(wc);
    }

}
