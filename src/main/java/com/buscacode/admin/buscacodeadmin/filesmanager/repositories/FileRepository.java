package com.buscacode.admin.buscacodeadmin.filesmanager.repositories;
import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.File;

public interface FileRepository extends CrudRepository<File,Long>{
  
}
