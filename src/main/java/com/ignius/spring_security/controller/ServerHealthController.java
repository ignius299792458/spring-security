package com.ignius.spring_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/health")
public class ServerHealthController {

    @GetMapping
    public Mono<ResponseEntity<String>> health() {
        return Mono.just(ResponseEntity.ok("<div><h1>Spring Security Server Health</h1> <p>Server health is good <p></div>"));
    }
}
