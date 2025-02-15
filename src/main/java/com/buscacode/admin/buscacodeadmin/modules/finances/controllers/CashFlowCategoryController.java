package com.buscacode.admin.buscacodeadmin.modules.finances.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.CashFlowCategory;
import com.buscacode.admin.buscacodeadmin.modules.finances.services.CashFlowCategoryService;

@RestController
@RequestMapping("/cash-flow-categories")
public class CashFlowCategoryController {
  @Autowired
  private CashFlowCategoryService cashFlowCategoryService;

  @GetMapping
  public List<CashFlowCategory> getMethodName() {
    return cashFlowCategoryService.getAllMainCategories();
  }

}
