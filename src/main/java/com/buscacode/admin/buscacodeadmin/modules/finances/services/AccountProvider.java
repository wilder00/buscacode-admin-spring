package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Account;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Currency;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto.AccountDTO;
import com.buscacode.admin.buscacodeadmin.modules.finances.enums.AccountState;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.AccountRepository;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.CurrencyRepository;
import com.buscacode.admin.buscacodeadmin.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class AccountProvider implements AccountService {
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private CurrencyRepository currencyRepository;
  @Autowired
  private UserService userService;

  @Override
  public List<Account> getMyAccounts() {
    User loggedUser = userService.getAuthenticatedUser();
    return accountRepository.getAllByOwner_id(loggedUser.getId());
  }

  @Transactional
  @Override
  public Account CreateMyAccount(AccountDTO accountBody) {
    User loggedUser = userService.getAuthenticatedUser();

    Currency currency = currencyRepository.findById(accountBody.getCurrencyId())
        .orElseThrow(() -> new RequestRejectedException("Currency not found"));

    Account account = new Account();
    account.setOwner(loggedUser);
    account.setName(accountBody.getName());
    account.setCreatedBy(loggedUser);
    account.setCurrency(currency);
    account.setState(AccountState.ACTIVE);
    Account savedAccount = accountRepository.save(account);
    return savedAccount;
  }

}
