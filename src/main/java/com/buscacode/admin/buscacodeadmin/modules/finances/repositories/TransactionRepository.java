package com.buscacode.admin.buscacodeadmin.modules.finances.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
  public List<Transaction> getAllByCreatedBy_Username(String username);

  public List<Transaction> getAllByAccount_id(String id);
}
