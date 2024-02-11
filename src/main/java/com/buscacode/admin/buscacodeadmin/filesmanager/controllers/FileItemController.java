package com.buscacode.admin.buscacodeadmin.filesmanager.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.entities.Product;
import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.filesmanager.services.FileService;
import com.buscacode.admin.buscacodeadmin.filesmanager.services.FolderService;
import com.buscacode.admin.buscacodeadmin.filesmanager.utils.ResponseValidationMessage;
import com.buscacode.admin.buscacodeadmin.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/file-items")
public class FileItemController {

  @Autowired
  private FileService fileService;
  @Autowired
  private FolderService folderService;
  @Autowired
  private UserService userService;

  @GetMapping
  public List<File> getFileItems(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    
    
    return fileService.getAllByUsername(loggedUsername);
  }

  @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
  public ResponseEntity<?> insertFile(@Valid @ModelAttribute File file, BindingResult result) throws IllegalStateException, IOException{
    if(result.hasFieldErrors()) {
      ResponseValidationMessage validator = ResponseValidationMessage.INSTANCE;
      return validator.validation(result);
    }
    Map<String,String> body = new HashMap<>();
    
    if (file == null || file.getFile() == null) {
      body.put("error", "File cannot be null");
      return ResponseEntity.badRequest().body(body);
    }
    System.out.println("SHOUL not  BE HEERE in save post");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    Optional<User> optionalUser = userService.findByUsername(loggedUsername);
    if(optionalUser.isPresent()){
      file.setCreatedBy(optionalUser.get());
    }
    File newFile = fileService.save(file);

    if (newFile == null) {
      body.put("error", "No se ha creado el file.");
      return ResponseEntity.internalServerError().body(body);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(newFile);
  } 

}
