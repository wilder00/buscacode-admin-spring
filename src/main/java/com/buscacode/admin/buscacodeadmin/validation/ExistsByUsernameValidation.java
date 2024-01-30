package com.buscacode.admin.buscacodeadmin.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.buscacode.admin.buscacodeadmin.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

  @Autowired
  private UserService service;

  @Override
  public boolean isValid(String username, ConstraintValidatorContext context) {
    
    return !service.existByUsername(username);
  }
  
}
