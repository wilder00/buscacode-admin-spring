package com.buscacode.admin.buscacodeadmin.services.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.buscacode.admin.buscacodeadmin.entities.User;

public interface CredentialService {
  List<User> findAll();

  Optional<User> findById(UUID id);

  User save(User recurso);

  Optional<User> update(UUID id, User recurso);

  Optional<User> delete(User recurso);

  User getAuthenticatedUser();
}
