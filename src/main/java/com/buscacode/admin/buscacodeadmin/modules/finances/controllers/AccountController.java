package com.buscacode.admin.buscacodeadmin.modules.finances.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.modules.filesmanager.utils.ResponseValidationMessage;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Account;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto.AccountDTO;
import com.buscacode.admin.buscacodeadmin.modules.finances.services.AccountService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  public List<Account> getMyAccounts() {
    return accountService.getMyAccounts();
  }

  @PostMapping
  public ResponseEntity<?> createMyAccount(@Valid @RequestBody(required = true) AccountDTO accountBody,
      BindingResult result) {
    if (result.hasFieldErrors()) {
      ResponseValidationMessage validator = ResponseValidationMessage.INSTANCE;
      return validator.validation(result);
    }
    Map<String, String> body = new HashMap<>();

    Account createdAccount = null;

    try {
      createdAccount = accountService.CreateMyAccount(accountBody);
    } catch (Exception e) {
      System.err.println("error message");
      System.out.println(e.toString());
      body.put("message", e.getMessage());
    }

    if (createdAccount == null) {
      body.put("error", "Cannot be created");
      return ResponseEntity.internalServerError().body(body);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
  }

}
