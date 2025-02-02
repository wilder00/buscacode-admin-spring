package com.buscacode.admin.buscacodeadmin.modules.filesmanager.services;

import java.util.List;
import java.util.Optional;

import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.File;

public interface FileService {

  public List<File> getAllByUsername(String username);

  public List<File> getAllByUsernameAndFolderId(String username, Long fileId);

  public Optional<File> findFileByIdAndUsername(Long fileId, String username);

  public File save(File file);

}
