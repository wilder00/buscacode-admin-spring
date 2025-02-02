package com.buscacode.admin.buscacodeadmin.modules.filesmanager.services;

import java.util.List;
import java.util.Optional;

import com.buscacode.admin.buscacodeadmin.modules.filesmanager.dto.FolderCreateDTO;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.Folder;

public interface FolderService {
  public List<Folder> getFoldersByUsername(String username);

  public List<Folder> getAllByIdAndUsername(Long id, String username);

  public List<Folder> getAllByFolderFatherIdAndUsername(Long id, String username);

  public Optional<Folder> findFolderByIdAndUsername(Long id, String username);

  public Folder save(Folder folder);

  public Optional<Folder> getFolderById(Long id);

  public Folder getRootFolder();

  public Folder createUserFolder(Long id, FolderCreateDTO folderCreateDTO);
}
