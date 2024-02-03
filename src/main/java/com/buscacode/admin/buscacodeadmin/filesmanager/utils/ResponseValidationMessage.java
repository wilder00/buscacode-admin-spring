package com.buscacode.admin.buscacodeadmin.filesmanager.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

//singleton
public enum ResponseValidationMessage {
  INSTANCE;
  
  //BindingResult result debe estar al costado derecho proximo del @valid
   public ResponseEntity<?> validation(BindingResult result) {
    Map<String, String> errors = new HashMap<>();
    
    result.getFieldErrors().forEach(err -> {
      errors.put(err.getField(), "El campo "+ err.getField() + " " + err.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(errors);
  }
}
