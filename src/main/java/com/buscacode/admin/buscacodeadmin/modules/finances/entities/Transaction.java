package com.buscacode.admin.buscacodeadmin.modules.finances.entities;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.modules.finances.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(length = 36, nullable = false, updatable = false, columnDefinition = "CHAR(36)")
  private String id;

  private String name;

  @Enumerated(EnumType.STRING)
  @NotNull
  private TransactionType type;

  @NotNull
  @Min(value = 0, message = "Price must be greater than 0")
  private Double amount;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  @JsonIgnoreProperties(value = { "createdBy" })
  private Account account;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "created_by", referencedColumnName = "username")
  @JsonIgnoreProperties(value = { "roles", "email", "isAdmin" })
  private User createdBy;

  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @Column(name = "updated_at", insertable = false, updatable = false)
  @JsonIgnore
  private Date updatedAt;

  @Column(name = "deleted_at", insertable = false, updatable = true)
  @JsonIgnore
  private Date deletedAt;

  @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnoreProperties(value = { "transaction", "createdBy" })
  private TransactionDetail transactionDetail;

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

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }

  public TransactionDetail getTransactionDetail() {
    return transactionDetail;
  }

  public void setTransactionDetail(TransactionDetail transactionDetail) {
    this.transactionDetail = transactionDetail;
  }

}
