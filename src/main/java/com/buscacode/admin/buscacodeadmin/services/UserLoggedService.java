package com.buscacode.admin.buscacodeadmin.services;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.repositories.UserRepository;

@org.springframework.stereotype.Service
public class UserLoggedService {
  @Autowired
  private UserRepository userRepository;

  @Transactional(readOnly = true)
  public Optional<User> findById(UUID id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isEmpty()) {
      Hibernate.initialize(userOptional.get().getRoles());
    }

    return userOptional;
  }

  @Transactional(readOnly = true)
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
