package com.buscacode.admin.buscacodeadmin.modules.filesmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.repositories.FileExplorerRepository;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.repositories.FileRepository;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.repositories.FolderRepository;

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

  @Transactional(readOnly = true)
  @Override
  public Optional<File> findFileByIdAndUsername(Long fileId, String username) {
    return fileRepository.findByIdAndCreatedBy_Username(fileId, username);
  }

  @Transactional
  @Override
  public File save(File file) {
    if (file == null)
      return null;
    String username = file.getCreatedBy().getUsername();

    String originalName = file.getFile().getOriginalFilename();
    originalName = fileExplorerRepository.sanitizeFileName(originalName);
    String newName = "u_" + username + ".f_" + file.getFolder().getId() + "." + originalName;

    Optional<Folder> folderOptional = folderRepository.findByIdAndCreatedBy_Username(file.getFolder().getId(),
        username);
    if (!folderOptional.isPresent()) {
      return null;
    }

    file.setFolder(folderOptional.get());
    file.setTypeFile(file.getFile().getContentType());
    System.out.println("the file type: =>>> " + file.getFile().getContentType());

    java.io.File fileInSystem = fileExplorerRepository.saveMultipartFile(file.getFile(), newName, username);
    if (fileInSystem == null)
      return null;

    // String originalName = file.getFile().getOriginalFilename();
    if (file.getName() == null || file.getName().isEmpty()) {
      file.setName(fileInSystem.getName());
    }
    file.setOriginalName(file.getFile().getOriginalFilename());
    file.setAbsolutePath(fileInSystem.getAbsolutePath());
    file.setSavedName(fileInSystem.getName());

    File newFile = fileRepository.save(file);
    newFile.setPath("/file-items/s/" + newFile.getId());
    try {
      newFile = fileRepository.save(newFile);
    } catch (Exception e) {
      try {
        java.nio.file.Files.deleteIfExists(fileInSystem.toPath());
      } catch (Exception ef) {
        newFile = null;
      }
    }
    return newFile;
  }

}
