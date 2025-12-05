package com.buscacode.admin.buscacodeadmin.modules.general.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tags")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @NotEmpty
  @NotNull
  private String name;

  // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(name = "user_id", nullable = false, columnDefinition = "BINARY(16)")
  @JsonIgnore
  private UUID userId;

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

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

}
