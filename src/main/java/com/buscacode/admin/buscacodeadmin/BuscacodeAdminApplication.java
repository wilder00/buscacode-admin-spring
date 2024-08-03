package com.buscacode.admin.buscacodeadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BuscacodeAdminApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuscacodeAdminApplication.class, args);
  }

}
