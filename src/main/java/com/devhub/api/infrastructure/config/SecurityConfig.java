package com.devhub.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Mantemos desabilitado para facilitar os testes agora
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/resources/**").permitAll() // Qualquer um pode ver os links
                        .anyRequest().authenticated() // Para votar ou postar, precisa estar logado
                )
                .oauth2Login(Customizer.withDefaults()); // Habilita o login do GitHub

        return http.build();
    }
}
