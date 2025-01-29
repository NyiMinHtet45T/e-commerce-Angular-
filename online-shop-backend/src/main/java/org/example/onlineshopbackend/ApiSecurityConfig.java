package org.example.onlineshopbackend;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.security.ApiSecurityExceptionHandler;
import org.example.onlineshopbackend.security.JwtTokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApiSecurityConfig {

    private final JwtTokenAuthenticationFilter authenticationFilter;
    private final ApiSecurityExceptionHandler apiSecurityExceptionHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(c -> c.configurationSource(corsConfiguration()));
        http.authorizeHttpRequests(request -> {
//            request.requestMatchers("/security/**", "/api/product/**", "/api/category/**", "/api/review/**").permitAll();
//            request.requestMatchers("/api/admin/category/**", "/api/admin/product/**").hasAuthority("ADMIN");
//            request.requestMatchers("/api/cart/**", "/api/order/**").hasAuthority("CUSTOMER");
//            request.requestMatchers("/api/user/**", "/api/address/**").authenticated();
            request.anyRequest().permitAll();
        });
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(exception -> {
            exception.accessDeniedHandler(apiSecurityExceptionHandler);
            exception.authenticationEntryPoint(apiSecurityExceptionHandler);
        });
        return http.build();
    }

    private CorsConfigurationSource corsConfiguration() {
        return request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:5173"));
            configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
            configuration.addAllowedHeader("*");
            configuration.setAllowCredentials(true);
            return configuration;
        };
    }
}
