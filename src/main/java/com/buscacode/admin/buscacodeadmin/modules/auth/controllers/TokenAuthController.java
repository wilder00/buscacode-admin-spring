package com.buscacode.admin.buscacodeadmin.modules.auth.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.modules.auth.dto.RefreshTokenDto;
import com.buscacode.admin.buscacodeadmin.modules.auth.services.TokenAuthService;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.utils.ResponseValidationMessage;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Transaction;
import com.buscacode.admin.buscacodeadmin.services.UserService;

import ch.qos.logback.core.subst.Token;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/oauth")
public class TokenAuthController {
  @Autowired
  private TokenAuthService tokenAuthService;

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshToken(@Valid @RequestBody(required = true) RefreshTokenDto refreshTokenDto,
      BindingResult result) {

    if (result.hasFieldErrors()) {
      ResponseValidationMessage validator = ResponseValidationMessage.INSTANCE;
      return validator.validation(result);
    }

    Map<String, String> body = new HashMap<>();

    try {
      // createdTransaction = transactionService.CreateMyTransaction(transactionBody);
      String newToken = tokenAuthService.getNewAccessToken(refreshTokenDto.getRefreshToken());
      body.put("accessToken", newToken);
    } catch (Exception e) {
      body.put("message2", e.getMessage());
      body.put("error", "invalid_token");
      body.put("message", "El token JWT ha expirado!");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(body);
  }
}
