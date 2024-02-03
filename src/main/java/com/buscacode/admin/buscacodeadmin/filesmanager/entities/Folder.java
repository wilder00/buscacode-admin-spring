package com.buscacode.admin.buscacodeadmin.filesmanager.entities;

import java.util.Date;
import java.util.List;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="folders")
public class Folder {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private String name;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String pathOfIds;

  @Transient
  private Long folderFatherId;

  @ManyToOne
  @JoinColumn(name = "folder_father_id", referencedColumnName = "id", insertable = false, updatable = false)
  @JsonIgnoreProperties(value={"folders", "folderFather", "createdBy"})
  private Folder folderFather;

  @OneToMany(mappedBy = "folderFather", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties(value = {"folders", "folderFather", "createdBy"})
  private List<Folder> folders;

  private String description;
  
  @ManyToOne
  @JoinColumn(name = "created_by", referencedColumnName = "username")
  @JsonIgnoreProperties("roles")
  private User createdBy;

  private Date createdAt;
  private Date updatedAt;
  private Date deletedAt;

  @PrePersist
  public void prePersist() {
    if(this.folderFather == null) return;
    if(this.folderFather.folderFather == null){
      this.pathOfIds = this.folderFather.getId() + "";
    } else {
      this.pathOfIds = String.format("%s/%d", this.folderFather.getPathOfIds(), this.folderFather.getId());
    }
  }
  @PostLoad
  public void postLoad(){
    if(this.folderFather == null) return;
    this.folderFatherId = this.folderFather.getId();
    if(this.folderFather.folderFather == null){
      this.pathOfIds = this.folderFather.getId() + "";
    } else {
      this.pathOfIds = String.format("%s/%d", this.folderFather.getPathOfIds(), this.folderFather.getId());
    }
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

  public String getPathOfIds() {
    return pathOfIds;
  }

  public void setPathOfIds(String pathOfIds) {
    this.pathOfIds = pathOfIds;
  }

  public Long getFolderFatherId() {
    return folderFatherId;
  }

  public void setFolderFatherId(Long folderFatherId) {
    this.folderFatherId = folderFatherId;
  }

  public Folder getFolderFather() {
    return folderFather;
  }

  public void setFolderFather(Folder folderFather) {
    this.folderFather = folderFather;
  }

  public List<Folder> getFolders() {
    return folders;
  }

  public void setFolders(List<Folder> folders) {
    this.folders = folders;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
