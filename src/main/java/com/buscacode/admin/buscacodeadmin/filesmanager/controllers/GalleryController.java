package com.buscacode.admin.buscacodeadmin.filesmanager.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.filesmanager.entities.Gallery;
import com.buscacode.admin.buscacodeadmin.filesmanager.services.GalleryService;

@RestController
@RequestMapping(value = "/gallery")
public class GalleryController {
  @Autowired
  private GalleryService galleryService;

  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  @GetMapping
  public ResponseEntity<?> View() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String loggedUsername = authentication.getName();

    Optional<Gallery> galleryOptional = galleryService.findByUsername(loggedUsername);
    if (galleryOptional.isPresent()) {
      return ResponseEntity.ok(galleryOptional.orElseThrow());
    }

    return ResponseEntity.notFound().build();
  }
}
