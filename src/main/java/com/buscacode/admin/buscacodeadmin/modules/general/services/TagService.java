package com.buscacode.admin.buscacodeadmin.modules.general.services;

import java.util.List;

import com.buscacode.admin.buscacodeadmin.modules.general.entities.Tag;

public interface TagService {

  public List<Tag> getAllMyTagsByName(String name);

  public Tag saveTag(Tag tag);
}
