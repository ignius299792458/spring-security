package com.ignius.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        return httpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/login", "/logout").permitAll()
                        .pathMatchers("/public/**").permitAll()
                        .pathMatchers("/health/**").hasAnyRole("ADMIN")
                        .pathMatchers("/actuator/**").hasAuthority("ADMIN")
                        .anyExchange().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("ignius")
                .password("ignius")
                .authorities("ADMIN")
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user);
    }
}