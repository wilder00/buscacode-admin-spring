package com.buscacode.admin.buscacodeadmin.modules.general.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.buscacode.admin.buscacodeadmin.modules.general.entities.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

  public List<Tag> getAllByUserId(UUID userId);

  @Query("SELECT t FROM Tag t WHERE t.userId = :userId AND (:name IS NULL OR t.name LIKE %:name%)")
  List<Tag> findAllByUserIdAndName(@Param("userId") UUID userId, @Param("name") String name);
}
