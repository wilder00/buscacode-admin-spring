package com.buscacode.admin.buscacodeadmin.filesmanager.services;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional(readOnly = true)
  @Override
  public List<File> getAllByUsername(String username) {
    
    return StreamSupport.stream(fileRepository.getAllByCreatedBy_Username(username).spliterator(), false)
      .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public List<File> getAllByUsernameAndFolderId(String username, Long fileId) {

    return StreamSupport.stream(fileRepository.getAllByUsernameAndFolderId(username, fileId).spliterator(), false)
      .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public File save(File file) {
    if(file == null) return null;
    String username = file.getCreatedBy().getUsername();
    java.io.File fileInSystem = fileExplorerRepository.saveMultipartFile(file.getFile(), username);
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
