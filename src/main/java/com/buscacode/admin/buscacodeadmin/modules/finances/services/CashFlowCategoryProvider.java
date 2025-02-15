package com.buscacode.admin.buscacodeadmin.modules.finances.services;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.CashFlowCategory;
import com.buscacode.admin.buscacodeadmin.modules.finances.repositories.CashFlowCategoryRepository;

import jakarta.persistence.EntityManager;

@Service
public class CashFlowCategoryProvider implements CashFlowCategoryService {

  @Autowired
  private CashFlowCategoryRepository cashFlowCategoryRepository;

  @Autowired
  private EntityManager entityManager;

  @Transactional(readOnly = true)
  @Override
  public List<CashFlowCategory> getAllMainCategories() {
    Session session = entityManager.unwrap(Session.class);
    Filter filter = session.enableFilter("enabledCashFlowCategory");
    filter.setParameter("enabled", true);

    List<CashFlowCategory> categories = cashFlowCategoryRepository.findAllByEnabledIsTrueAndCategoryFatherIsNull();
    return categories;
  }

}
