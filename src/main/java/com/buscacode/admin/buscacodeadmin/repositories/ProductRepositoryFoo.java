package com.buscacode.admin.buscacodeadmin.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;

import com.buscacode.admin.buscacodeadmin.models.Product;
import com.buscacode.admin.buscacodeadmin.repositories.interfaces.Repository;

@Primary
@org.springframework.stereotype.Repository
public class ProductRepositoryFoo implements Repository<Product> {
  @Override
  public List<Product> findAll() {
    // TODO Auto-generated method stub
    return Collections.singletonList(new Product(1L, "Monitor asus 2", 600L));
  }

  @Override
  public Product findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

}
