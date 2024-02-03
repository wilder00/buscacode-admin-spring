package com.buscacode.admin.buscacodeadmin.filesmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.filesmanager.services.FolderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value="/api/folders")
public class FolderController { 

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
  
}
