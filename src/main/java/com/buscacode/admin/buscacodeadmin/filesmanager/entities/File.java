package com.buscacode.admin.buscacodeadmin.filesmanager.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.web.multipart.MultipartFile;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="files")
public class File {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  //@NotEmpty
  private String name;
  //@NotEmpty
  private String originalName;
  private String Path;
  @JsonIgnore
  private String absolutePath;
  private String extension;
  private String description;
  private String url;
  private String typeFile;

  //private Long folderId;
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "folder_id", referencedColumnName = "id", insertable = false, updatable = false)
  @JsonIgnoreProperties(value={"folders", "folderFather", "createdBy"})
  private Folder folder;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "created_by", referencedColumnName = "username")
  @JsonIgnoreProperties("roles")
  private User createdBy;
  private Date createdAt;
  private Date updatedAt;
  private Date deletedAt;

  @Transient
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private MultipartFile file;

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

  public String getOriginalName() {
    return originalName;
  }

  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }

  public String getPath() {
    return Path;
  }

  public void setPath(String path) {
    Path = path;
  }

  public String getAbsolutePath() {
    return absolutePath;
  }

  public void setAbsolutePath(String absolutePath) {
    this.absolutePath = absolutePath;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTypeFile() {
    return typeFile;
  }

  public void setTypeFile(String typeFile) {
    this.typeFile = typeFile;
  }

  public Folder getFolder() {
    return folder;
  }

  public void setFolder(Folder folder) {
    this.folder = folder;
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

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile document) {
    this.file = document;
  }


  
}
