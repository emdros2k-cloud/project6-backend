package com.aivle12.book_backend.config;

import com.aivle12.book_backend.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // /users/signup, /users/login은 누구나 접근 가능
                        .requestMatchers("/users/signup", "/users/login").permitAll()
                        
                        // /users/me는 로그인 필요
                        .requestMatchers("/users/me").authenticated()

                        // 도서 등록, 수정, 삭제
                        .requestMatchers(HttpMethod.POST, "/books").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/books/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/books/*").authenticated()

                        // 표지 저장, AI 표지 생성
                        .requestMatchers(HttpMethod.PATCH, "/books/*/cover").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/books/*/cover-editor").authenticated()
                        .requestMatchers(HttpMethod.POST, "/books/cover/generate").authenticated()
                        .requestMatchers(HttpMethod.POST, "/books/*/cover/generate").authenticated()

                        // 즐겨찾기
                        .requestMatchers(HttpMethod.POST, "/books/*/favorites").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/books/*/favorites").authenticated()

                        // 댓글 작성, 수정, 삭제
                        .requestMatchers(HttpMethod.POST, "/books/*/comments").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/comments/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/comments/*").authenticated()

                        // 팔로우, 언팔로우, 팔로잉/팔로워 목록
                        .requestMatchers(HttpMethod.POST, "/authors/*/follows").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/authors/*/follows").authenticated()
                        .requestMatchers("/users/followings", "/users/followers").authenticated()

                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
