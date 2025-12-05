package com.buscacode.admin.buscacodeadmin.modules.finances.entities;

import java.util.Date;
import java.util.List;

import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.modules.general.entities.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_details")
public class TransactionDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "transaction_id", nullable = false, unique = true)
  @JsonIgnoreProperties(value = { "transactionDetail", "createdBy" })
  private Transaction transaction;

  @ManyToOne
  @JoinColumn(name = "file_id", nullable = true, unique = false)
  private File file;

  @ManyToOne
  @JoinColumn(name = "cash_flow_category_id", nullable = true, unique = false)
  @JsonIgnoreProperties(value = { "subCategories" })
  private CashFlowCategory cashFlowCategory;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "transition_details_tags", joinColumns = @JoinColumn(name = "transaction_detail_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))

  private List<Tag> tags;

  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @Column(name = "updated_at", insertable = false, updatable = false)
  @JsonIgnore
  private Date updatedAt;

  @Column(name = "deleted_at", insertable = false, updatable = true)
  @JsonIgnore
  private Date deletedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
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

  public CashFlowCategory getCashFlowCategory() {
    return cashFlowCategory;
  }

  public void setCashFlowCategory(CashFlowCategory cashFlowCategory) {
    this.cashFlowCategory = cashFlowCategory;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

}
