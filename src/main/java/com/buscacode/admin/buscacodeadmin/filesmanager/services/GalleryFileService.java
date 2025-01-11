package com.buscacode.admin.buscacodeadmin.filesmanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Gallery;
import com.buscacode.admin.buscacodeadmin.filesmanager.repositories.GalleryRepository;

@Service
public class GalleryFileService implements GalleryService {
  @Autowired
  private GalleryRepository galleryRepository;

  @Transactional(readOnly = true)
  @Override
  public Optional<Gallery> findByIdAndUsername(String id, String username) {
    return galleryRepository.findByIdAndCreatedBy_Username(id, username);
  }

  @Override
  public Optional<Gallery> findByUsername(String username) {
    return galleryRepository.findByCreatedBy_Username(username);
  }

  @Transactional
  @Override
  public Gallery save(Gallery gallery) {
    return galleryRepository.save(gallery);
  }

}
