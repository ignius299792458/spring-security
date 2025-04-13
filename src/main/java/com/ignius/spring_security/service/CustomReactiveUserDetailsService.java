package com.ignius.spring_security.service;

import com.ignius.spring_security.dto.UserDto;
import com.ignius.spring_security.model.User;
import com.ignius.spring_security.repositoy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + username)))
                .map(this::createUserDetails);
    }

    private UserDetails createUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }

    public Mono<User> getUser(Long userId){
        return userRepository.findById(userId);
    }

    public Mono<User> saveUser(UserDto user) {
        User newUser = new User(user.getUsername(), user.getPassword());
        return userRepository.save(newUser);
    }

    public Mono<Void> deleteUser(Long userId) {
        return userRepository.deleteById(userId);
    }
}