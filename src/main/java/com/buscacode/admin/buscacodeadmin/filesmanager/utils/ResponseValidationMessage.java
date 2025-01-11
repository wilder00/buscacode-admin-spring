package com.buscacode.admin.buscacodeadmin.filesmanager.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

//singleton
public enum ResponseValidationMessage {
  INSTANCE;

  // BindingResult result debe estar al costado derecho proximo del @valid
  public ResponseEntity<?> validation(BindingResult result) {
    Map<String, String> data = new HashMap<>();
    Map<String, Object> error = new HashMap<>();
    error.put("message", "Field validation error");
    error.put("data", data);

    result.getFieldErrors().forEach(err -> {
      data.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(error);
  }
}
