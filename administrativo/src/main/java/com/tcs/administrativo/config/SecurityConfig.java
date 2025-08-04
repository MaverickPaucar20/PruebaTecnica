package com.tcs.administrativo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF solo si usas solo APIs REST (no formularios)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite TODO sin autenticación
                );
        return http.build();
    }
}
