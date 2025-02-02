package com.buscacode.admin.buscacodeadmin.modules.finances.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Currency;
import com.buscacode.admin.buscacodeadmin.modules.finances.services.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

  @Autowired
  CurrencyService currencyService;

  @GetMapping
  public List<Currency> getAllCurrencies() {
    return currencyService.getAll();
  }

}
