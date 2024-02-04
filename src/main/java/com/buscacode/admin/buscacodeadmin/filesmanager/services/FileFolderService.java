package com.buscacode.admin.buscacodeadmin.filesmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.filesmanager.repositories.FolderRepository;

@Service
public class FileFolderService implements FolderService {

  @Autowired
  private FolderRepository folderRepository;

  @Transactional(readOnly = true)
  @Override
  public List<Folder> getFoldersByUsername(String username) {
    return StreamSupport.stream(folderRepository.getAllByCreatedBy_Username(username).spliterator(), false)
      .collect(Collectors.toList());
  }

  @Override
  public Optional<Folder> findFolderByIdAndUsername(Long id, String username) {

    return folderRepository.findByIdAndCreatedBy_Username(id, username);
  }

  @Override
  public List<Folder> getAllByIdAndUsername(Long id, String username) {
    return folderRepository.getAllByIdAndCreatedBy_username(id, username);
  }

  
}
