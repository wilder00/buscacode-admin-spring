package com.buscacode.admin.buscacodeadmin.filesmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FolderCreateDTO {
  @NotNull(message = "Folder name is required")
  @Size(min = 1, max = 255, message = "Folder name must be between 1 and 255 characters")
  private String name;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
