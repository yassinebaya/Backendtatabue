package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/").permitAll();
                    authConfig.requestMatchers("/user/**").authenticated();
                    authConfig.requestMatchers("/admin/**").denyAll();

                })
                .formLogin(Customizer.withDefaults()) // Login with browser and Form
                .httpBasic(Customizer.withDefaults()); // Login with Insomnia and Basic Auth
        return http.build();
    }
    @Bean
    UserDetailsService userDetailsService() {
        var admin = User.builder()
                .username("Willy De Keyser")
                .password("{noop}password")
                .roles("USER", "ADMIN")
                .build();
        var user = User.builder()
                .username("Ken De Keyser")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

}

