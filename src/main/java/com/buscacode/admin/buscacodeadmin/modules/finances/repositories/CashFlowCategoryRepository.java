package com.buscacode.admin.buscacodeadmin.modules.finances.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.CashFlowCategory;

public interface CashFlowCategoryRepository extends CrudRepository<CashFlowCategory, Long> {

  public List<CashFlowCategory> findAllByEnabledIsTrueAndCategoryFatherIsNull();
}
