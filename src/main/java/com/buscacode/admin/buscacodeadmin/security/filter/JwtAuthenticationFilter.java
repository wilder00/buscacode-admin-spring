package com.buscacode.admin.buscacodeadmin.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig;
import com.buscacode.admin.buscacodeadmin.security.model.UserDetail;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import static com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private final SecretKey SECRET_KEY;
  private final SecretKey SECRET_REFRESH_KEY;
  private final String CONTENT_TYPE;
  private TokenJwtConfig tokenJwtConfig;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenJwtConfig tokenJwtConfig) {
    this.authenticationManager = authenticationManager;
    SECRET_KEY = tokenJwtConfig.getSecretKey();
    SECRET_REFRESH_KEY = tokenJwtConfig.getRefreshSecretKey();
    CONTENT_TYPE = TokenJwtConfig.CONTENT_TYPE;
    this.tokenJwtConfig = tokenJwtConfig;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    User user = null;
    String username = null;
    String password = null;

    try {
      user = new ObjectMapper().readValue(request.getInputStream(), User.class);
      username = user.getUsername();
      password = user.getPassword();

    } catch (StreamReadException e) {
      e.printStackTrace();
    } catch (DatabindException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
        password);
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    return authentication;

  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {

    // org.springframework.security.core.userdetails.User user =
    // (org.springframework.security.core.userdetails.User)
    // authResult.getPrincipal();
    UserDetail user = (UserDetail) authResult.getPrincipal();
    String username = user.getUsername();
    UUID id = user.getId();
    Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

    Claims claims = Jwts.claims()
        .add("id", id)
        .add("authorities", new ObjectMapper().writeValueAsString(roles))
        .add("username", username)
        .build();

    String token = Jwts.builder()
        .subject(username)
        .claims(claims)
        .expiration(new Date(System.currentTimeMillis() + this.tokenJwtConfig.getExpirationTimeAccessToken()))
        .issuedAt(new Date())
        .signWith(SECRET_KEY)
        .compact();

    String refreshToken = Jwts.builder()
        .subject(username)
        .claims(claims)
        .expiration(new Date(System.currentTimeMillis() + this.tokenJwtConfig.getExpirationTimeRefreshToken()))
        .issuedAt(new Date())
        .signWith(SECRET_REFRESH_KEY)
        .compact();

    // response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
    Map<String, String> body = new HashMap<>();
    body.put("id", id.toString());
    body.put("accessToken", token);
    body.put("refreshToken", refreshToken);
    body.put("username", username);
    body.put("message", String.format("Hola, %s. Has iniciado sesión!", username));

    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    response.setContentType(CONTENT_TYPE);
    response.setStatus(200);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    // TODO Auto-generated method stub

    Map<String, String> body = new HashMap<>();
    body.put("message", "Error en la autenticación username o password incorrectos!");
    body.put("error", failed.getMessage());

    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    response.setStatus(401);
    response.setContentType(CONTENT_TYPE);
  }

}
