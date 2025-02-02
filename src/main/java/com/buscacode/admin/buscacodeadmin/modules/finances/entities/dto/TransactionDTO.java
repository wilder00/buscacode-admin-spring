package com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransactionDTO {

  @NotBlank(message = "The account name cannot be empty")
  private String name;

  private String type;

  @NotNull
  @Min(value = 0, message = "Amount must be greater than 0")
  private Double amount;

  @NotBlank(message = "The account name cannot be empty")
  @Size(min = 36, max = 36, message = "The account id cannot be empty")
  private String accountId;

  private Long fileId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public Long getFileId() {
    return fileId;
  }

  public void setFileId(Long fileId) {
    this.fileId = fileId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
