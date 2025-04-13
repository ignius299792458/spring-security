package com.ignius.spring_security.service;

import com.ignius.spring_security.repositoy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDetailService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;


    public Mono<UserDetails> findByUsername(String username);
}
