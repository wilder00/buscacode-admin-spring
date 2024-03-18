package com.buscacode.admin.buscacodeadmin.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenJwtConfig {
  
  //public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
  public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode("algunaLaveSecretamuYLargaljdsfljsdfhfja34ro23jjajdf"));
  public static final String PREFIX_TOKEN = "Bearer ";
  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String CONTENT_TYPE = "application/json";

  private SecretKey secretKey;

  public TokenJwtConfig() {
  }

  public TokenJwtConfig(String secretKeySeeder) {
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeySeeder));
  }

  public SecretKey getSecretKey() {
    return this.secretKey;
  }
}
