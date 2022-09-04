package com.example.demoit2.service;


import com.example.demoit2.entity.items.TagEntity;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<TagEntity> findTagById(Long id);

    Optional<TagEntity> findTagsByItemId(Long id);

    List<TagEntity> findAllTagsByName(String substring);

    List<TagEntity> findAllTags();

    Optional<TagEntity> saveTag(TagEntity tag);

    void deleteTag(Long id);

    void addTagToItem(String name, Long itemId);
}
