package com.buscacode.admin.buscacodeadmin.security.filter;

import static com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig.CONTENT_TYPE;
import static com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig.PREFIX_TOKEN;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.buscacode.admin.buscacodeadmin.security.SimpleGrantedAuthorityJsonCreator;
import com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter{

  private final SecretKey SECRET_KEY;

  public JwtValidationFilter(AuthenticationManager authenticationManager, TokenJwtConfig tokenJwtConfig) {
    super(authenticationManager);
    SECRET_KEY = tokenJwtConfig.getSecretKey();
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    String header = request.getHeader(HEADER_AUTHORIZATION);

    if(header == null || !header.startsWith(PREFIX_TOKEN)){
      chain.doFilter(request, response);
      return;
    }
    String token = header.replace(PREFIX_TOKEN, "");

    try {
      Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
      //String username = claims.getSubject();
      String username2 = (String) claims.get("username");
      Object authoritiesClaims = claims.get("authorities");

      Collection<? extends GrantedAuthority> authorities = Arrays.asList(
        new ObjectMapper()
          .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
          .readValue(
            authoritiesClaims.toString()
            .getBytes(), SimpleGrantedAuthority[].class
          )
      );

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username2, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      
      chain.doFilter(request, response);

    } catch (JwtException e) {
      Map<String, String> body = new HashMap<>();
      body.put("error", e.getMessage());
      body.put("message", "El token JWT es inválido!");

      response.getWriter().write(new ObjectMapper().writeValueAsString(body));
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType(CONTENT_TYPE);
    }
  }

  
}
