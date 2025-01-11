package com.buscacode.admin.buscacodeadmin.filesmanager.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Gallery;

public interface GalleryRepository extends CrudRepository<Gallery, String> {

  public Optional<Gallery> findByIdAndCreatedBy_Username(String id, String username);

  public Optional<Gallery> findByCreatedBy_Username(String username);

}
