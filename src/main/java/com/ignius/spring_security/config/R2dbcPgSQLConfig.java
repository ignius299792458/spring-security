package com.ignius.spring_security.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class R2dbcPgSQLConfig {
    @Value("${spring.r2dbc.host}")
    private String host;

    @Value("${spring.r2dbc.port}")
    private Integer port;

    @Value("${spring.r2dbc.password}")
    private String password;

    @Value("${spring.r2dbc.name}")
    private String database;

    @Value("${spring.r2dbc.username}")
    private String username;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(host)
                .port(port)
                .database(database)
                .username(username)
                .password(password)
                .build()
        );
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager() {
        return new R2dbcTransactionManager(connectionFactory());
    }

    @Bean
    public TransactionalOperator transactionalOperator() {
        return TransactionalOperator.create(reactiveTransactionManager());
    }

}
