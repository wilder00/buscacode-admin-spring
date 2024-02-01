package com.buscacode.admin.buscacodeadmin.entities;

import java.util.List;

import com.buscacode.admin.buscacodeadmin.validation.ExistsByUsername;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ExistsByUsername
  @Column(unique = true)
  @NotBlank
  private String username;

  @NotBlank
  @Size(min=4)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @NotBlank
  private String name;

  @NotBlank
  private String lastname;

  @Email
  private String email;

  @ManyToMany
  @JoinTable(
    name="users_roles",
    joinColumns= @JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
  )
  private List<Role> roles;
  
  private Boolean isEnabled;

  //Indica que es solo para el modelo y no para base de datos
  @Transient
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private boolean isAdmin;

  @PrePersist
  public void prePersist() {
    if(isEnabled == null){
      isEnabled = true;
    }
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  
  
}
