package com.buscacode.admin.buscacodeadmin.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;

import com.buscacode.admin.buscacodeadmin.models.Product;
import com.buscacode.admin.buscacodeadmin.repositories.interfaces.Repository;
import com.buscacode.admin.buscacodeadmin.services.interfaces.Service;

@org.springframework.stereotype.Service
public class ProductService implements Service<Product>{

  // @Autowired no es necesario por que ya tenemos el constructor
  private Repository<Product> repository;

  private final Double IMPUESTO_PERCENT = 0.18;

  // El qualifier es para escoger cuál implementación utilizar
  public ProductService(@Qualifier("productRepository") Repository<Product> repository) {
    this.repository = repository;
  }

  public List<Product> findAll () {
    return repository.findAll().stream().map(p -> {
      Double priceImp = p.getPrice() * IMPUESTO_PERCENT;
      Product newProduct = new Product(p);
      newProduct.setPrice(newProduct.getPrice() + priceImp.longValue());
      return newProduct;
    }).collect(Collectors.toList());
  }

  public Product findById (Long id) {
    return repository.findById(id);
  }

}
