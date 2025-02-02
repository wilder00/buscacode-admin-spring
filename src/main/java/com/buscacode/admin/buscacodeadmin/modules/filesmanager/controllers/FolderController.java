package com.buscacode.admin.buscacodeadmin.modules.filesmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.entities.Product;
import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.dto.FolderCreateDTO;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.services.FileService;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.services.FolderService;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.utils.ResponseValidationMessage;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/folders")
public class FolderController {

  @Autowired
  private FileService fileService;
  @Autowired
  private FolderService fileFolderService;

  @GetMapping
  public List<Folder> getFolders() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    return fileFolderService.getFoldersByUsername(loggedUsername);
  }

  @GetMapping("/{folderId}")
  public ResponseEntity<?> getFolderById(@PathVariable Long folderId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();

    Optional<Folder> folderOptional = fileFolderService.findFolderByIdAndUsername(folderId, loggedUsername);

    if (folderOptional.isPresent()) {
      return ResponseEntity.ok(folderOptional.orElseThrow());
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{folderId}/folders")
  public List<Folder> getFolderByIdAndUsername(@PathVariable Long folderId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    return fileFolderService.getAllByFolderFatherIdAndUsername(folderId, loggedUsername);
  }

  @GetMapping("/{folderId}/files")
  public List<File> getFileItems(@PathVariable Long folderId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    return fileService.getAllByUsernameAndFolderId(loggedUsername, folderId);
  }

  @PostMapping("/{folderId}")
  public ResponseEntity<?> postUserFolder(
      @Valid @RequestBody(required = true) FolderCreateDTO folderData,
      BindingResult result,
      @PathVariable Long folderId) throws IllegalStateException, IOException {
    if (result.hasFieldErrors()) {
      ResponseValidationMessage validator = ResponseValidationMessage.INSTANCE;
      return validator.validation(result);
    }
    Map<String, String> body = new HashMap<>();

    Folder createdFolder = null;
    try {
      createdFolder = fileFolderService.createUserFolder(folderId, folderData);
    } catch (Exception e) {
      body.put("error", e.getMessage());
    }

    if (createdFolder == null) {
      body.put("error", "Cannot be created");
      return ResponseEntity.internalServerError().body(body);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(createdFolder);
  }
}
