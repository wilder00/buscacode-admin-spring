package com.buscacode.admin.buscacodeadmin.modules.auth.services;

public interface TokenAuthService {

  public String getToken(String token);

  public String getNewAccessToken(String refreshToken) throws Exception;

}
