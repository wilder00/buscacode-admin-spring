package com.buscacode.admin.buscacodeadmin.filesmanager.services;

import java.util.List;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.File;

public interface FileService {

  public List<File> getAllByUsername(String username);
  public List<File> getAllByUsernameAndFolderId(String username, Long fileId);
  public File save(File user);
  
}
