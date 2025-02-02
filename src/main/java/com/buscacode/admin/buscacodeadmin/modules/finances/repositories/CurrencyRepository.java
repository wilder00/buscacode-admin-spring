package com.buscacode.admin.buscacodeadmin.modules.finances.repositories;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}
