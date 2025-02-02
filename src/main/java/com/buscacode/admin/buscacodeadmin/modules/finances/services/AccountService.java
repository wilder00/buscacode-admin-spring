package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Account;
import com.buscacode.admin.buscacodeadmin.modules.finances.entities.dto.AccountDTO;

public interface AccountService {

  public List<Account> getMyAccounts();

  public Account CreateMyAccount(AccountDTO accountBody);
}
