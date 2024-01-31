package com.buscacode.admin.buscacodeadmin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.buscacode.admin.buscacodeadmin.security.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private AuthenticationConfiguration authenticationConfiguration;

  @Bean
  AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests((auths) -> auths
      .requestMatchers(HttpMethod.GET,"/api/users").permitAll()
      .requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
      .anyRequest().authenticated()
    )
    .addFilter(new JwtAuthenticationFilter(authenticationManager()))
    .csrf(config -> config.disable()) //d
    .sessionManagement(management-> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //para que la sescion http no tenga estado y se maneje por tokens
    .build();
  }
}
