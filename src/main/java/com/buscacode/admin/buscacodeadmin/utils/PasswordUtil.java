package com.buscacode.admin.buscacodeadmin.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public static String encryptPassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  public static void main(String[] args) {
    String rawPassword = "password";
    String encryptedPassword = encryptPassword(rawPassword);
    System.out.println("Encrypted Password: " + encryptedPassword);
  }
}
