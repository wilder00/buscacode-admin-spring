package com.buscacode.admin.buscacodeadmin.filesmanager.services;

import java.util.List;
import java.util.Optional;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;

public interface FolderService {
  public List<Folder> getFoldersByUsername(String username);
  public List<Folder>  getAllByIdAndUsername(Long id, String username);
  public List<Folder>  getAllByFolderFatherIdAndUsername(Long id, String username);
  public Optional<Folder> findFolderByIdAndUsername(Long id, String username);
}
