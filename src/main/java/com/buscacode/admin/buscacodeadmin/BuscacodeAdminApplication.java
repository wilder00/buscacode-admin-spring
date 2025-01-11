package com.buscacode.admin.buscacodeadmin;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class BuscacodeAdminApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuscacodeAdminApplication.class, args);
  }

  @PostConstruct
  public void init() {
    // Set the default timezone to America/Lima
    TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
  }
}
