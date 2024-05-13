package com.example.recipe_20240425.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class AuthConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails chef = User
                .withUsername("chef")
                .password(passwordEncoder().encode("chef1"))
                .roles("CHEF")
                .build();

        UserDetails assist = User
                .withUsername("assist")
                .password(passwordEncoder().encode("assist1"))
                .roles("ASSISTANT")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user1"))
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(chef, assist, user);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
