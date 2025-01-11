package com.buscacode.admin.buscacodeadmin.filesmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.entities.Product;
import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.filesmanager.dto.FolderCreateDTO;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.filesmanager.repositories.FolderRepository;
import com.buscacode.admin.buscacodeadmin.services.UserService;

@Service
public class FileFolderService implements FolderService {
  private final Long ROOT_FOLDER_ID = 1L;
  @Autowired
  private FolderRepository folderRepository;
  @Autowired
  private UserService userService;

  @Transactional(readOnly = true)
  @Override
  public List<Folder> getFoldersByUsername(String username) {
    return StreamSupport.stream(folderRepository.getAllByCreatedBy_Username(username).spliterator(), false)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Folder> findFolderByIdAndUsername(Long id, String username) {

    return folderRepository.findByIdAndCreatedBy_Username(id, username);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Folder> getAllByIdAndUsername(Long id, String username) {
    return folderRepository.getAllByIdAndCreatedBy_username(id, username);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Folder> getAllByFolderFatherIdAndUsername(Long id, String username) {
    if (id == 0)
      id = ROOT_FOLDER_ID;
    return folderRepository.findByFolderFather_idAndCreatedBy_Username(id, username);
  }

  @Transactional
  @Override
  public Folder save(Folder folder) {
    return folderRepository.save(folder);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Folder> getFolderById(Long id) {
    return folderRepository.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public Folder getRootFolder() {
    return folderRepository.findById(ROOT_FOLDER_ID).get();
  }

  @Transactional
  @Override
  public Folder createUserFolder(Long id, FolderCreateDTO folderCreateDTO) {
    User user = userService.getAuthenticatedUser();

    Folder folderFather = findFolderByIdAndUsername(id, user.getUsername()).get();

    Folder folder = new Folder();
    folder.setName(folderCreateDTO.getName());
    folder.setDescription(folderCreateDTO.getDescription());
    folder.setFolderFather(folderFather);
    folder.setCreatedBy(user);
    folder.setCreatedAt(new java.util.Date());
    return folderRepository.save(folder);

  }

}
