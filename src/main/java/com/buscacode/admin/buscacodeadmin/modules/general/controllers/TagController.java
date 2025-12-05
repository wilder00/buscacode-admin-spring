package com.buscacode.admin.buscacodeadmin.modules.general.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.modules.general.entities.Tag;
import com.buscacode.admin.buscacodeadmin.modules.general.services.TagService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tags")
public class TagController {

  @Autowired
  private TagService tagService;

  @GetMapping
  public List<Tag> getMyTags(@RequestParam(required = false, name = "q") String name) {
    return tagService.getAllMyTagsByName(name);
  }

  @PostMapping
  public Tag postMethodName(@RequestBody Tag tag) {
    return tagService.saveTag(tag);
  }

}
