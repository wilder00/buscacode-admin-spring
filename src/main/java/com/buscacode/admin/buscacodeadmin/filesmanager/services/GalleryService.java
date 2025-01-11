package com.buscacode.admin.buscacodeadmin.filesmanager.services;

import java.util.Optional;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Gallery;

public interface GalleryService {
  public Optional<Gallery> findByIdAndUsername(String id, String username);

  public Optional<Gallery> findByUsername(String username);

  public Gallery save(Gallery gallery);

}
