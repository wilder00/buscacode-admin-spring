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
  private FolderRepository forderRepository;

  @Transactional
  @Override
  public List<Folder> getFoldersByUsername(String username) {
    return StreamSupport.stream(forderRepository.getAllByCreatedBy_Username(username).spliterator(), false)
      .collect(Collectors.toList());
  }


}
