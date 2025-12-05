package com.buscacode.admin.buscacodeadmin.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenJwtConfig {

  // public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
  // public static final SecretKey SECRET_KEY = Keys
  // .hmacShaKeyFor(Decoders.BASE64.decode("algunaLaveSecretamuYLargaljdsfljsdfhfja34ro23jjajdf"));
  // public static final SecretKey REFRESH_SECRET_KEY = Keys
  // .hmacShaKeyFor(Decoders.BASE64.decode("anotherSecretKeyForRefreshTokenanotherSecretKeyForRefreshToken"));
  public static final String PREFIX_TOKEN = "Bearer ";
  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String CONTENT_TYPE = "application/json";

  private SecretKey secretKey;
  private SecretKey refreshSecretKey;
  private JwtParser jwtParserSecretKey;
  private JwtParser jwtParserRefreshSecretKey;

  public TokenJwtConfig() {
  }

  public TokenJwtConfig(String secretKeySeeder, String refreshSecretKeySeeder) {
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeySeeder));
    this.refreshSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecretKeySeeder));
    this.jwtParserSecretKey = Jwts.parser().verifyWith(this.secretKey).build();
    this.jwtParserRefreshSecretKey = Jwts.parser().verifyWith(this.refreshSecretKey).build();
  }

  public SecretKey getSecretKey() {
    return this.secretKey;
  }

  public SecretKey getRefreshSecretKey() {
    return this.refreshSecretKey;
  }

  public JwtParser getJwtParserSecretKey() {
    return jwtParserSecretKey;
  }

  public JwtParser getJwtParserRefreshSecretKey() {
    return jwtParserRefreshSecretKey;
  }

  public Long getExpirationTimeHour() {
    final long second = 1000L;
    final long minute = 60 * second;
    final long hour = 60 * minute;
    return hour;
  }

  public Long getExpirationTimeDay() {
    final long day = 24 * this.getExpirationTimeHour();
    return day;
  }

  public Long getExpirationTimeAccessToken() {
    return this.getExpirationTimeHour();
  }

  public Long getExpirationTimeRefreshToken() {
    return this.getExpirationTimeDay();
  }
}
