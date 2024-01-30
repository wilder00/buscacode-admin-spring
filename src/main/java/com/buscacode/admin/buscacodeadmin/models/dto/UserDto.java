package com.buscacode.admin.buscacodeadmin.models.dto;

import com.buscacode.admin.buscacodeadmin.models.UserBack;

public class UserDto {
  private String title;
  private UserBack user;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public UserBack getUser() {
    return user;
  }
  public void setUser(UserBack user) {
    this.user = user;
  }
  

  
}
