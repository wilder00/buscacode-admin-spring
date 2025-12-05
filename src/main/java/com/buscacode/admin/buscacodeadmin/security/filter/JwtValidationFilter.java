package com.buscacode.admin.buscacodeadmin.security.filter;

import static com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig.CONTENT_TYPE;
import static com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig.PREFIX_TOKEN;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.repositories.UserRepository;
import com.buscacode.admin.buscacodeadmin.security.SimpleGrantedAuthorityJsonCreator;
import com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig;
import com.buscacode.admin.buscacodeadmin.services.UserLoggedService;
import com.buscacode.admin.buscacodeadmin.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

  private final SecretKey SECRET_KEY;
  private TokenJwtConfig tokenJwtConfig;
  private UserLoggedService userLoggedService;

  public JwtValidationFilter(AuthenticationManager authenticationManager, TokenJwtConfig tokenJwtConfig,
      UserLoggedService userLoggedService) {
    super(authenticationManager);
    SECRET_KEY = tokenJwtConfig.getSecretKey();
    this.tokenJwtConfig = tokenJwtConfig;
    this.userLoggedService = userLoggedService;
  }

  private String getTokenByCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    String token = null;
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("token".equals(cookie.getName())) {
          token = cookie.getValue();
        }
        if ("sod".equals(cookie.getName())) {
          token = cookie.getValue();
        }
      }
    }
    return token;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    String header = request.getHeader(HEADER_AUTHORIZATION);

    String token = null;
    if (header == null || !header.startsWith(PREFIX_TOKEN)) {
      token = getTokenByCookie(request);
      if (token == null) {
        chain.doFilter(request, response);
        return;
      }
    } else {
      token = header.replace(PREFIX_TOKEN, "");
    }

    try {
      Claims claims = this.tokenJwtConfig.getJwtParserSecretKey().parseSignedClaims(token).getPayload();
      // String username = claims.getSubject();
      String username2 = (String) claims.get("username");
      UUID uuid = UUID.fromString((String) claims.get("id"));
      Object authoritiesClaims = claims.get("authorities");

      Collection<? extends GrantedAuthority> authorities = Arrays.asList(
          new ObjectMapper()
              .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
              .readValue(
                  authoritiesClaims.toString()
                      .getBytes(),
                  SimpleGrantedAuthority[].class));

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username2, null,
          authorities);

      // call here the database to get all the user details
      Optional<User> userOptional = userLoggedService.findById(uuid);
      // .orElseThrow(() -> new JwtException("User not found"));
      if (userOptional.isEmpty()) {
        throw new JwtException("User not found");
      }
      User user = userOptional.get();
      authenticationToken.setDetails(user);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

      chain.doFilter(request, response);

    } catch (ExpiredJwtException e) {
      Map<String, String> body = new HashMap<>();
      body.put("error", "invalid_token");
      body.put("message", "El token JWT ha expirado!");

      response.setContentType(CONTENT_TYPE);
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setHeader("WWW-Authenticate",
          "Bearer error=\"invalid_token\", error_description=\"The access token expired\"");
      response.getWriter().write(new ObjectMapper().writeValueAsString(body));

    } catch (JwtException e) {
      Map<String, String> body = new HashMap<>();
      body.put("error", e.getMessage());
      body.put("message", "El token JWT es inválido!");

      response.setContentType(CONTENT_TYPE);
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    }
  }

}
