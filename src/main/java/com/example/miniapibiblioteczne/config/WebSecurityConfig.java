package com.example.miniapibiblioteczne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Książki
                        .requestMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")

                        // Wypożyczenia
                        .requestMatchers(HttpMethod.POST, "/api/borrowings/borrow").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/borrowings/return/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/borrowings/history/**").hasAnyRole("USER", "ADMIN")

                        // Użytkownicy
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() // rejestracja bez logowania
                        .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/api/books/**")
                        .ignoringRequestMatchers("/api/borrowings/**")
                        .ignoringRequestMatchers("/api/users/**")
                        .disable()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails superadmin = User.builder()
                .username("superadmin")
                .password(passwordEncoder.encode("hard"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin, superadmin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
