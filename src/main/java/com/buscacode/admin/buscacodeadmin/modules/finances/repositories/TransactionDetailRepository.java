package com.buscacode.admin.buscacodeadmin.modules.finances.repositories;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.modules.finances.entities.TransactionDetail;

public interface TransactionDetailRepository extends CrudRepository<TransactionDetail, Long> {

}
