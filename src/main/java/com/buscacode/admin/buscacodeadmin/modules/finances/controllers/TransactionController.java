package com.buscacode.admin.buscacodeadmin.modules.finances.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.modules.filesmanager.utils.ResponseValidationMessage;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Transaction;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto.TransactionDTO;
import com.buscacode.admin.buscacodeadmin.modules.finances.services.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @GetMapping
  public List<Transaction> getMyTransactions() {
    return transactionService.getMyTransactions();
  }

  @PostMapping
  public ResponseEntity<?> addTransaction(@Valid @RequestBody(required = true) TransactionDTO transactionBody,
      BindingResult result) {
    if (result.hasFieldErrors()) {
      ResponseValidationMessage validator = ResponseValidationMessage.INSTANCE;
      return validator.validation(result);
    }
    Map<String, String> body = new HashMap<>();

    Transaction createdTransaction = null;

    try {
      createdTransaction = transactionService.CreateMyTransaction(transactionBody);
    } catch (Exception e) {
      body.put("message", e.getMessage());
    }
    if (createdTransaction == null) {
      body.put("error", "Cannot be created");
      return ResponseEntity.internalServerError().body(body);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
  }
}
