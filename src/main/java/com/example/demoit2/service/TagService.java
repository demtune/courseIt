package com.example.demoit2.service;


import com.example.demoit2.entity.items.TagEntity;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<?> findTagById(Long id);

    Optional<?> findTagsByItemId(Long id);

    List<TagEntity> findAllTagsByName(String substring);

    Optional<?> findAllTags();

    Optional<?> saveTag(TagEntity tag);

    void deleteTag(Long id);

    void addTagToItem(Long tagId, Long itemId);
}
