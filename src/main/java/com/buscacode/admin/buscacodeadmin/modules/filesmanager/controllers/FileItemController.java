package com.buscacode.admin.buscacodeadmin.modules.filesmanager.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.entities.Product;
import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.File;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.services.FileService;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.services.FolderService;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.utils.ResponseValidationMessage;
import com.buscacode.admin.buscacodeadmin.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/file-items")
public class FileItemController {

  @Autowired
  private FileService fileService;
  @Autowired
  private FolderService folderService;
  @Autowired
  private UserService userService;

  @GetMapping
  public List<File> getFileItems() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    return fileService.getAllByUsername(loggedUsername);
  }

  @GetMapping("/s/{fileId}")
  public ResponseEntity<?> displayFile(@PathVariable Long fileId) throws MalformedURLException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();

    Optional<File> fileOptional = fileService.findFileByIdAndUsername(fileId, loggedUsername);

    if (fileOptional.isPresent()) {
      File savedFile = fileOptional.get();
      Path filePath = Paths.get(fileOptional.get().getAbsolutePath());

      // Serve the file if it exists
      Resource resource = new UrlResource(filePath.toUri());
      return ResponseEntity.ok()
          // .header("Content-Disposition", "attachment; filename=\"" +
          // resource.getFilename() + "\"")
          /*
           * .header("Content-Disposition", "inline; filename=\"" +
           * savedFile.getOriginalName() + "\"")
           */
          .header("Content-Disposition", "inline; filename=\"" +
              URLEncoder.encode(savedFile.getOriginalName(), StandardCharsets.UTF_8).replace("+", " ")
              + "\"; filename*=UTF-8''" +
              URLEncoder.encode(savedFile.getOriginalName(), StandardCharsets.UTF_8))
          .header(HttpHeaders.CONTENT_TYPE, savedFile.getTypeFile())
          .body(resource);
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{fileId}")
  public ResponseEntity<?> getFileById(@PathVariable Long fileId) throws MalformedURLException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();

    Optional<File> fileOptional = fileService.findFileByIdAndUsername(fileId, loggedUsername);

    if (!fileOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
        .body(fileOptional.get());
  }

  @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
  public ResponseEntity<?> insertFile(@Valid @ModelAttribute File file, BindingResult result)
      throws IllegalStateException, IOException {
    if (result.hasFieldErrors()) {
      ResponseValidationMessage validator = ResponseValidationMessage.INSTANCE;
      return validator.validation(result);
    }
    Map<String, String> body = new HashMap<>();

    if (file == null || file.getFile() == null) {
      body.put("message", "File cannot be null");
      return ResponseEntity.badRequest().body(body);
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();
    Optional<User> optionalUser = userService.findByUsername(loggedUsername);
    if (optionalUser.isPresent()) {
      file.setCreatedBy(optionalUser.get());
    }

    File newFile = fileService.save(file);

    if (newFile == null) {
      body.put("message", "No se ha creado el file.");
      return ResponseEntity.internalServerError().body(body);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(newFile);
  }

}
