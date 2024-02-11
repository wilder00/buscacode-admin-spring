package com.buscacode.admin.buscacodeadmin.filesmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.entities.Product;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.filesmanager.services.FileService;
import com.buscacode.admin.buscacodeadmin.filesmanager.services.FolderService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value="/api/folders")
public class FolderController { 

  @Autowired
  private FileService fileService;
  @Autowired
  private FolderService fileFolderService;
  

  @GetMapping
  public List<Folder> getFolders() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("===================>>>");
    System.out.println(authentication.getName());
    String loggedUsername = authentication.getName();
    return fileFolderService.getFoldersByUsername(loggedUsername);
  }
  @GetMapping("/{folderId}")
  public ResponseEntity<?> getFolderById(@PathVariable Long folderId){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();

    Optional<Folder> folderOptional = fileFolderService.findFolderByIdAndUsername(folderId, loggedUsername);

    if(folderOptional.isPresent()){
      return ResponseEntity.ok(folderOptional.orElseThrow());
    }

    return  ResponseEntity.notFound().build();
  }

  @GetMapping("/{folderId}/folders")
  public List<Folder> getFolderByIdAndUsername(@PathVariable Long folderId){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    return fileFolderService.getAllByFolderFatherIdAndUsername(folderId, loggedUsername );
  }

  @GetMapping("/{folderId}/files")
  public List<File> getFileItems(@PathVariable Long folderId){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    return fileService.getAllByUsernameAndFolderId(loggedUsername, folderId);
  }

  
  
}
