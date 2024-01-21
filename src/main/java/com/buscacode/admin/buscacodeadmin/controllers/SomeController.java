package com.buscacode.admin.buscacodeadmin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.models.Product;
import com.buscacode.admin.buscacodeadmin.services.interfaces.Service;

@RestController
@RequestMapping("/api/products")
public class SomeController {

  @Autowired
  private Service<Product> service;
  @GetMapping
  public List<Product> list() {

    return service.findAll();
  }
  
  @GetMapping("/{productId}")
  public Product show(@PathVariable Long productId) {

    return service.findById(productId);
  }
}
