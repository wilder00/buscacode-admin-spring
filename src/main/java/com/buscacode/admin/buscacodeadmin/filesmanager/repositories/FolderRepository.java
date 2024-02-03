package com.buscacode.admin.buscacodeadmin.filesmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;

public interface FolderRepository extends CrudRepository<Folder,Long>{

  public List<Folder> getAllByCreatedBy_Username(String username);
}
