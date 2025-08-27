package com.example.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Step 1: Create an in-memory user with username & password
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")       // username
                .password("password")   // password
                .roles("USER")          // role
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // Step 2: Define security rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for testing APIs
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // every request needs authentication
            )
            .httpBasic(Customizer.withDefaults()); // use HTTP Basic Authentication
        return http.build();
    }
}
