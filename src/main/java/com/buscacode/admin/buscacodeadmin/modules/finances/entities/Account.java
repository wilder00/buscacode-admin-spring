package com.buscacode.admin.buscacodeadmin.modules.finances.entities;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.modules.finances.enums.AccountState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(length = 36, nullable = false, updatable = false, columnDefinition = "CHAR(36)")
  private String id;

  private String name;

  @Column(name = "current_balance", insertable = false, updatable = false, columnDefinition = "DECIMAL(10,2)", nullable = false, precision = 2)
  private Double currentBalance;

  private Date closedAt;

  @Enumerated(EnumType.STRING)
  @NotNull
  private AccountState state;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "owner_id", referencedColumnName = "id")
  @JsonIgnoreProperties(value = { "roles", "email", "isAdmin", "isEnabled" })
  private User owner;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "currency_id", referencedColumnName = "id")
  private Currency currency;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "created_by", referencedColumnName = "username")
  @JsonIgnoreProperties("roles")
  private User createdBy;

  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @Column(name = "updated_at", insertable = false, updatable = false)
  @JsonIgnore
  private Date updatedAt;

  @Column(name = "deleted_at", insertable = false, updatable = true)
  @JsonIgnore
  private Date deletedAt;

  @PrePersist
  private void ensureDefaults() {
    if (currentBalance == null) {
      currentBalance = 0.0;
    }
  }

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

  public Double getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(Double currentBalance) {
    this.currentBalance = currentBalance;
  }

  public Date getClosedAt() {
    return closedAt;
  }

  public void setClosedAt(Date closedAt) {
    this.closedAt = closedAt;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
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

  public AccountState getState() {
    return state;
  }

  public void setState(AccountState state) {
    this.state = state;
  }

}
