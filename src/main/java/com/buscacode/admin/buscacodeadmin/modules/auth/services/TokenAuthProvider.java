package com.buscacode.admin.buscacodeadmin.modules.auth.services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.security.TokenJwtConfig;
import com.buscacode.admin.buscacodeadmin.services.UserLoggedService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service
public class TokenAuthProvider implements TokenAuthService {

  @Autowired
  private TokenJwtConfig tokenJwtConfig;

  @Autowired
  private UserLoggedService userLoggedService;

  @Override
  public String getToken(String token) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getToken'");
  }

  @Override
  public String getNewAccessToken(String refreshToken) throws Exception {
    System.out.println("the refresh secret: " + tokenJwtConfig.getRefreshSecretKey());
    Claims claims = null;
    claims = this.tokenJwtConfig.getJwtParserRefreshSecretKey()
        .parseSignedClaims(refreshToken).getPayload();

    UUID uuid = UUID.fromString((String) claims.get("id"));
    Optional<User> optionUser = userLoggedService.findById(uuid);
    if (!optionUser.isPresent()) {
      return ResponseEntity.status(401).build().toString();
    }

    User user = optionUser.get();

    List<Map<String, String>> rolesList = user.getRoles().stream()
        .map(role -> {
          Map<String, String> roleMap = new HashMap<>();
          roleMap.put("authority", role.getName()); // or getAuthority()
          return roleMap;
        })
        .collect(Collectors.toList());

    String jsonRoles = new ObjectMapper().writeValueAsString(rolesList);

    Claims newClaims = Jwts.claims()
        .add("id", user.getId())
        .add("authorities", jsonRoles)
        .add("username", user.getUsername())
        .build();

    String token = Jwts.builder()
        .subject(user.getUsername())
        .claims(newClaims)
        .expiration(new Date(System.currentTimeMillis() + tokenJwtConfig.getExpirationTimeAccessToken()))
        .issuedAt(new Date())
        .signWith(tokenJwtConfig.getSecretKey())
        .compact();

    return token;
  }
}
