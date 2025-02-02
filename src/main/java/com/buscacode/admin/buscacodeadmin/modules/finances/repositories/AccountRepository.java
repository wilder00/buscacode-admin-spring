package com.buscacode.admin.buscacodeadmin.modules.finances.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Account;

public interface AccountRepository extends CrudRepository<Account, String> {

  public List<Account> getAllByOwner_id(UUID id);
}
