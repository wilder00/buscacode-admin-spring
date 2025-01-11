package com.buscacode.admin.buscacodeadmin.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Missing or invalid request body");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
