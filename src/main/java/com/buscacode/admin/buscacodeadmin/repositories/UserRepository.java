package com.buscacode.admin.buscacodeadmin.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.entities.User;

public interface UserRepository extends CrudRepository<User,UUID> {

  boolean existsByUsername(String username);
  Optional<User> findByUsername(String username);
}
