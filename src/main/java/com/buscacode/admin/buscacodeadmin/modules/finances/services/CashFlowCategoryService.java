package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.CashFlowCategory;

public interface CashFlowCategoryService {

  public List<CashFlowCategory> getAllMainCategories();
}
