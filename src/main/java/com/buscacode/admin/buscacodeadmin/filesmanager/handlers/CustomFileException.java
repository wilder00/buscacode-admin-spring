package com.buscacode.admin.buscacodeadmin.filesmanager.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.buscacode.admin.buscacodeadmin.filesmanager.handlers.exceptions.FileExistsException;

@ControllerAdvice
public class CustomFileException {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<Map<String, String>> handleMaxSizeException(MaxUploadSizeExceededException exc) {
      
      Map<String, String> errorResponse = new HashMap<>();
      errorResponse.put("error", "File size exceeds the maximum limit allowed.");

      return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
  }
  
  @ExceptionHandler(FileExistsException.class)
  public ResponseEntity<Map<String, String>> handleMaxSizeException(FileExistsException exc) {
      
      Map<String, String> errorResponse = new HashMap<>();
      errorResponse.put("error", "File already exists.");

      return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
  }
}
