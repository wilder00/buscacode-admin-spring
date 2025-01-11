package com.buscacode.admin.buscacodeadmin.events;

import org.springframework.context.ApplicationEvent;

import com.buscacode.admin.buscacodeadmin.entities.User;

public class UserCreatedEvent extends ApplicationEvent {
  private final User user;

  public UserCreatedEvent(Object source, User user) {
    super(source);
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
