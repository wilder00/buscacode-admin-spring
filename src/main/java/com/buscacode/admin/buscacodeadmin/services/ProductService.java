package com.buscacode.admin.buscacodeadmin.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.entities.Product;
import com.buscacode.admin.buscacodeadmin.repositories.ProductRepository;
//import com.buscacode.admin.buscacodeadmin.models.Product;
import com.buscacode.admin.buscacodeadmin.repositories.interfaces.Repository;
import com.buscacode.admin.buscacodeadmin.services.interfaces.Service;

@org.springframework.stereotype.Service
public class ProductService implements Service<Product>{

  @Autowired
  private ProductRepository repository;

  @Transactional(readOnly = true)
  @Override
  public List<Product> findAll() {
    return StreamSupport
            .stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Product> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  @Override
  public Product save(Product producto) {
    
    return repository.save(producto);
  }

  @Transactional
  @Override
  public Optional<Product> update(Long id, Product product) {
    Optional<Product> productOptional = repository.findById(id);

    if(productOptional.isPresent()){
      Product productDb = productOptional.orElseThrow();

      productDb.setName(product.getName());
      productDb.setPrice(product.getPrice());
      productDb.setDescription(product.getDescription());
      return Optional.of(repository.save(productDb));
    }

    return productOptional;
  }

  @Transactional
  @Override
  public Optional<Product> delete(Product product) {
    Optional<Product> productOptional = repository.findById(product.getId());
    productOptional.ifPresent(productDb -> {
      repository.delete(productDb);
    });

    return productOptional;
  }

  

}
