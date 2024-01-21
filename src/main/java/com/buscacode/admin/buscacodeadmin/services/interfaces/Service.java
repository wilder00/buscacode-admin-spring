package com.buscacode.admin.buscacodeadmin.services.interfaces;

import java.util.List;

public interface Service<T> {
  List<T> findAll();
  T findById(Long id);
}
