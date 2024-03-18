package com.buscacode.admin.buscacodeadmin.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.buscacode.admin.buscacodeadmin.security.filter.JwtAuthenticationFilter;
import com.buscacode.admin.buscacodeadmin.security.filter.JwtValidationFilter;


@Configuration
@EnableMethodSecurity(prePostEnabled = true) //Para incluir reglas en los mismos controladores por roles, sino solo sería por aquí
@PropertySources({
	@PropertySource(value="classpath:security.properties", encoding = "UTF-8"),
})
public class SecurityConfig {
  @Value("${security.secret.seed:}")
  private String secretSeed;

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
  TokenJwtConfig secretKey() {
    return new TokenJwtConfig(secretSeed);
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests((auths) -> auths
      .requestMatchers(HttpMethod.GET,"/api/users").permitAll()
      .requestMatchers(HttpMethod.POST,"/api/users/register").permitAll()
      .requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN")
      .requestMatchers(HttpMethod.GET,"/api/products").hasAnyRole("ADMIN", "USER")
      .requestMatchers(HttpMethod.PUT,"/api/products/{id}").hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE,"/api/products/{id}").hasRole("ADMIN")
      .anyRequest().authenticated()
    )
    .addFilter(new JwtAuthenticationFilter(authenticationManager(), secretKey()))
    .addFilter(new JwtValidationFilter(authenticationManager(), secretKey()))
    .csrf(config -> config.disable()) //d
    .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
    .sessionManagement(management-> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //para que la sescion http no tenga estado y se maneje por tokens
    .build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOriginPatterns(Arrays.asList("http://localhost*", "https://wildertrujillo.com"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
    config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config); // /** le indicamos todas las rutas desde la raiz

    return source;
  }

  @Bean
  FilterRegistrationBean<CorsFilter> corsFilter() {
    FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
    corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return corsBean;
  }

}
