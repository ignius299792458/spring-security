package com.ignius.spring_security.controller;

import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.invoker.ReactiveHttpRequestValues;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/health")
public class ServerHealthController {

    @GetMapping
    public Mono<ResponseEntity<String>> health() {
        return Mono.just(ResponseEntity.ok("OK"));
    }
}
