package com.buscacode.admin.buscacodeadmin.modules.filesmanager.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.events.UserCreatedEvent;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.Folder;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.entities.Gallery;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.services.FolderService;
import com.buscacode.admin.buscacodeadmin.modules.filesmanager.services.GalleryService;

@Component
public class GalleryEventListener implements ApplicationListener<UserCreatedEvent> {

  @Autowired
  private GalleryService galleryService;
  @Autowired
  private FolderService folderService;

  // @EventListener
  @TransactionalEventListener
  public void onUserCreated(UserCreatedEvent event) {
    User user = event.getUser();

    Folder folderUserRoot = new Folder();
    folderUserRoot.setName(user.getUsername());
    folderUserRoot.setDescription(user.getUsername() + "'s root folder");
    folderUserRoot.setFolderFather(null);
    folderUserRoot.setCreatedBy(user);
    folderUserRoot.setCreatedAt(new java.util.Date());

    // Folder savedFolder = folderService.save(folderUserRoot);
    Folder savedFolder = folderUserRoot;
    Gallery gallery = new Gallery();
    gallery.setFolderRoot(savedFolder);
    gallery.setCreatedBy(user);
    gallery.setCreatedAt(new java.util.Date());

    galleryService.save(gallery);
  }

  @Override
  public void onApplicationEvent(@NonNull UserCreatedEvent event) {
    onUserCreated(event);
  }
}
