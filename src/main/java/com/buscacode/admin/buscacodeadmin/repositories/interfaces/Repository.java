package com.buscacode.admin.buscacodeadmin.repositories.interfaces;

import java.util.List;

public interface Repository<T> {
  List<T> findAll();
  T findById(Long id);
}
