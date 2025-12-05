package com.buscacode.admin.buscacodeadmin.modules.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RefreshTokenDto {
  @NotNull(message = "Refresh token is required")
  @Size(min = 65, message = "A valid token is required")
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

}
