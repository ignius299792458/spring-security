package com.ignius.spring_security.controller;

import com.ignius.spring_security.dto.UserDto;
import com.ignius.spring_security.model.User;
import com.ignius.spring_security.service.CustomReactiveUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private CustomReactiveUserDetailsService customReactiveUserDetailsService;


    @GetMapping("/:id")
    public Mono<ResponseEntity<User>> getUser(@RequestParam Long id) {
        return this.customReactiveUserDetailsService.getUser(id)
                .map(result -> ResponseEntity.ok().body(result))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<User>> createUser(@RequestBody UserDto user) {
        return this.customReactiveUserDetailsService.saveUser(user)
                .map(result -> ResponseEntity.ok().body(result))
                .onErrorReturn(ResponseEntity.badRequest().build());
    }
}
