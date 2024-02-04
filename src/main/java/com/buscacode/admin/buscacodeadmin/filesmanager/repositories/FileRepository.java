package com.buscacode.admin.buscacodeadmin.filesmanager.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.File;

public interface FileRepository extends CrudRepository<File,Long>{
  
  public List<File> getAllByCreatedBy_Username(String username);

  // Retrieve files by username and folder.id
  @Query("SELECT f FROM File f " +
          "WHERE f.createdBy.username = :username " +
          "AND f.folder.id = :folderId")
  public List<File> getAllByUsernameAndFolderId(@Param("username") String username, @Param("folderId") Long folderId);

}
