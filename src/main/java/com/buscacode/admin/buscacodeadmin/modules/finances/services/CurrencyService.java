package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Currency;

public interface CurrencyService {
  public List<Currency> getAll();
}
