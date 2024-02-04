package com.buscacode.admin.buscacodeadmin.filesmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;

public interface FolderRepository extends CrudRepository<Folder,Long>{

  public List<Folder> getAllByCreatedBy_Username(String username);
  public List<Folder> getAllByIdAndCreatedBy_username(Long id, String username);
  public Optional<Folder> findByIdAndCreatedBy_Username(Long id, String username);

  // @Query("SELECT f FROM File f " +
  //         "WHERE f.createdBy.username = :username " +
  //         "AND f.folder.id = :folderId")
  // public List<File> getAllByUsernameAndFolderId(@Param("username") String username, @Param("folderId") Long folderId);

}
