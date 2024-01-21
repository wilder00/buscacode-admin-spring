package com.buscacode.admin.buscacodeadmin.repositories;

import java.util.Arrays;
import java.util.List;

import com.buscacode.admin.buscacodeadmin.models.Product;
import com.buscacode.admin.buscacodeadmin.repositories.interfaces.Repository;;

@org.springframework.stereotype.Repository
public class ProductRepository implements Repository<Product>{

  private List<Product> data;

  public ProductRepository() {
    this.data = Arrays.asList(
      new Product(1L, "Memoria corsar 32", 300L),
      new Product(2L, "Monitor asus 16\"", 500L),
      new Product(3L, "Teclado Rizer Mini", 200L),
      new Product(4L, "Motherboard Gigabyte", 100L)
    );
  }

  @Override
  public List<Product> findAll() {
    return data;
  }
 
  @Override
  public Product findById(Long id) {

    //return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
  }

}
