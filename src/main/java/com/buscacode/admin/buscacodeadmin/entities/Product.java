package com.buscacode.admin.buscacodeadmin.entities;

import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  //@JsonRawValue
  //@NotEmpty
  @NotBlank(message="{NotBlank.product.description}")
  @Size(min=3, max=45)
  private String name;
  @Min(value=100, message="{Min.product.price}")
  @NotNull(message="{NotNull.product.price}")
  private Integer price;
  @NotEmpty(message="{NotEmpty.product.name}")
  private String description;
  
  
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
  public Integer getPrice() {
    return price;
  }
  public void setPrice(Integer price) {
    this.price = price;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }


  
}
