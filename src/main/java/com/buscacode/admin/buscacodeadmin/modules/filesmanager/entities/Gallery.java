package com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "galleries")
public class Gallery {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(length = 36, nullable = false, updatable = false, columnDefinition = "CHAR(36)")
  private String id;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "folder_root_id", referencedColumnName = "id", updatable = false)
  @JsonIgnoreProperties(value = { "folders", "createdBy", "folderFatherId", "folderFather" })
  private Folder folderRoot;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "created_by", referencedColumnName = "username")
  @JsonIgnoreProperties("roles")
  private User createdBy;
  private Date createdAt;
  private Date updatedAt;
  private Date deletedAt;

  /*
   * @PrePersist
   * private void generateId() {
   * if (this.id == null) {
   * this.id = UUID.randomUUID().toString();
   * }
   * }
   */

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Folder getFolderRoot() {
    return folderRoot;
  }

  public void setFolderRoot(Folder folderRoot) {
    this.folderRoot = folderRoot;
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

}
