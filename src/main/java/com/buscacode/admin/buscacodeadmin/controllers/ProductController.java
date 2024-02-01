package com.buscacode.admin.buscacodeadmin.controllers;

import java.util.HashMap;
// import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.buscacode.admin.buscacodeadmin.entities.Product;
import com.buscacode.admin.buscacodeadmin.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> list() {
    return productService.findAll();
  }

  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  @GetMapping("/{id}")
  public ResponseEntity<?> View(@PathVariable Long id) {
    Optional<Product> productOptional = productService.findById(id);
    if(productOptional.isPresent()){
      return ResponseEntity.ok(productOptional.orElseThrow());
    }

    return ResponseEntity.notFound().build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
    if(result.hasFieldErrors()) {
      return validation(result);
    }

    Product productNew = productService.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
  } 

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<?> update( @Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
    if(result.hasFieldErrors()) {
      return validation(result);
    }

    Optional<Product> productOptional = productService.update(id, product);
    if(productOptional.isPresent()){
      return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Product product = new Product();
    product.setId(id);
    Optional<Product> productOptional = productService.delete(product);

    if(productOptional.isPresent()){
      return ResponseEntity.ok(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }


   //BindingResult result debe estar al costado derecho proximo del @valid
   private ResponseEntity<?> validation(BindingResult result) {
    Map<String, String> errors = new HashMap<>();
    
    result.getFieldErrors().forEach(err -> {
      errors.put(err.getField(), "El campo "+ err.getField() + " " + err.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(errors);
  }
}
