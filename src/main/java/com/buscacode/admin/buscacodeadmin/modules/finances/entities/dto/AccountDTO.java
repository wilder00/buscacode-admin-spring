package com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AccountDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String id;

  @NotBlank(message = "The account name cannot be empty")
  private String name;

  @NotNull
  private Long currencyId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(Long currencyId) {
    this.currencyId = currencyId;
  }

}
