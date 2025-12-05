package com.buscacode.admin.buscacodeadmin.modules.general.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.modules.general.entities.Tag;
import com.buscacode.admin.buscacodeadmin.modules.general.repositories.TagRepository;
import com.buscacode.admin.buscacodeadmin.services.UserService;

@Service
public class TagProvider implements TagService {
  @Autowired
  private TagRepository tagRepository;
  @Autowired
  private UserService userService;

  @Override
  public List<Tag> getAllMyTagsByName(String name) {
    User loggedUser = userService.getAuthenticatedUser();
    List<Tag> tags = tagRepository.findAllByUserIdAndName(loggedUser.getId(), name);
    return tags;
  }

  @Override
  public Tag saveTag(Tag tag) {
    User loggedUser = userService.getAuthenticatedUser();
    tag.setUserId(loggedUser.getId());
    Tag savedTag = tagRepository.save(tag);
    return savedTag;
  }

}
