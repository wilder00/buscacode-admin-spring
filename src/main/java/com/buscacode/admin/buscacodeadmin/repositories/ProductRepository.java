package com.buscacode.admin.buscacodeadmin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
  
}
