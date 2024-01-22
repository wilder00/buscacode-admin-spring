package com.buscacode.admin.buscacodeadmin.services.interfaces;

import java.util.List;
import java.util.Optional;
public interface Service<T> {

  List<T> findAll();

  Optional<T> findById(Long id);

  T save(T recurso);
  Optional<T> update(Long id,  T recurso);

  Optional<T> delete(T recurso);

}
