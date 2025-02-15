package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.repositories.FileRepository;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Account;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.CashFlowCategory;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Transaction;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.TransactionDetail;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto.TransactionDTO;
import com.buscacode.admin.buscacodeadmin.modules.finances.enums.TransactionType;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.AccountRepository;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.CashFlowCategoryRepository;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.TransactionDetailRepository;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.TransactionRepository;
import com.buscacode.admin.buscacodeadmin.services.UserService;

@Service
public class TransactionProvider implements TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private CashFlowCategoryRepository cashFlowCategoryRepository;
  @Autowired
  private TransactionDetailRepository transactionDetailRepository;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private FileRepository fileRepository;

  @Transactional(readOnly = true)
  @Override
  public List<Transaction> getMyTransactions() {
    User loggedUser = userService.getAuthenticatedUser();
    return transactionRepository.getAllByCreatedBy_Username(loggedUser.getUsername());
  }

  @Transactional(readOnly = true)
  @Override
  public List<Transaction> getMyTransactionsByAccountId(String accountId) {
    User loggedUser = userService.getAuthenticatedUser();
    return transactionRepository.getAllByAccount_idAndCreatedBy_UsernameOrderByCreatedAtDesc(accountId,
        loggedUser.getUsername());
  }

  @Transactional
  @Override
  public Transaction CreateMyTransaction(TransactionDTO transactionBody) {
    User loggedUser = userService.getAuthenticatedUser();

    Account account = accountRepository.findById(transactionBody.getAccountId())
        .orElseThrow(() -> new RequestRejectedException("Account not found"));

    Transaction transaction = new Transaction();
    transaction.setName(transactionBody.getName());
    transaction.setType(TransactionType.valueOf(transactionBody.getType()));
    transaction.setAmount(transactionBody.getAmount());
    transaction.setAccount(account);
    transaction.setCreatedBy(loggedUser);

    transaction = transactionRepository.save(transaction);

    TransactionDetail detail = new TransactionDetail();

    detail.setTransaction(transaction);

    if (transactionBody.getFileId() != null) {
      File file = fileRepository.findById(transactionBody.getFileId())
          .orElseThrow(() -> new RequestRejectedException("File not found"));
      Boolean isFileOwner = file.getCreatedBy().getId().equals(loggedUser.getId());
      if (isFileOwner) {
        detail.setFile(file);
      }
    }

    CashFlowCategory cashFlowCategory = null;
    if (transactionBody.getCashFlowCategoryId() != null) {
      cashFlowCategory = cashFlowCategoryRepository.findById(transactionBody.getCashFlowCategoryId())
          .orElse(null);
    }
    detail.setCashFlowCategory(cashFlowCategory);
    detail = transactionDetailRepository.save(detail);
    transaction.setTransactionDetail(detail);

    return transaction;
  }

}
