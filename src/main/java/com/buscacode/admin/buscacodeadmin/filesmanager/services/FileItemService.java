package com.buscacode.admin.buscacodeadmin.filesmanager.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.filesmanager.repositories.FileExplorerRepository;
import com.buscacode.admin.buscacodeadmin.filesmanager.repositories.FileRepository;
import com.buscacode.admin.buscacodeadmin.filesmanager.repositories.FolderRepository;

@Service
public class FileItemService implements FileService {
  @Autowired
  private FileRepository fileRepository;
  @Autowired
  private FileExplorerRepository fileExplorerRepository;
  @Autowired
  private FolderRepository folderRepository;

  @Override
  public File save(File file) {
    if(file == null) return null;
    java.io.File fileInSystem = fileExplorerRepository.saveMultipartFile(file.getFile());
    if(fileInSystem == null) return null;

    String originalName = file.getFile().getOriginalFilename();
    if(file.getName()== null || file.getName().isEmpty()){
      file.setName(fileInSystem.getName());
    }
    file.setOriginalName(originalName);
    file.setAbsolutePath(fileInSystem.getAbsolutePath());

    File newFile = fileRepository.save(file);
    return newFile;
  }
  
}
