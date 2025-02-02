package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Currency;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.CurrencyRepository;

@Service
public class CurrencyProvider implements CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Transactional(readOnly = true)
  @Override
  public List<Currency> getAll() {
    return StreamSupport.stream(currencyRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

}
