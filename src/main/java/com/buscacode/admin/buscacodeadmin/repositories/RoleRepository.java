package com.buscacode.admin.buscacodeadmin.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {

  Optional<Role> findByName(String name);
}
