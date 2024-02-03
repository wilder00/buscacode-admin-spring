package com.buscacode.admin.buscacodeadmin.filesmanager.services;

import java.util.List;
import java.util.Optional;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;

public interface FolderService {
  List<Folder> getFoldersByUsername(String username);
}
