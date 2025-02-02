package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Transaction;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto.TransactionDTO;

public interface TransactionService {

  public List<Transaction> getMyTransactions();

  public Transaction CreateMyTransaction(TransactionDTO transactionBody);
}
