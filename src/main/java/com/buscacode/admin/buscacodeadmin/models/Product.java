package com.buscacode.admin.buscacodeadmin.models;

public class Product {
  private Long id;
  private String name;
  private Long price;
  
  public Product() {
  }
  
  public Product(Long id, String name, Long price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  // Un constructor para clonar
  public Product(Product producto) {
    this.id = producto.id;
    this.name = producto.name;
    this.price = producto.price;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Long getPrice() {
    return price;
  }
  public void setPrice(Long price) {
    this.price = price;
  }

  
}
