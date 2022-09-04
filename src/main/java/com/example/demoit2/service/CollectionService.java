package com.example.demoit2.service;

import com.example.demoit2.entity.collection.CollectionEntity;

import java.util.List;
import java.util.Optional;

public interface CollectionService {
    Optional<?> findCollectionById(Long id);

    Optional<?> saveCollection(CollectionEntity collection);

    Optional<?> getCollections();

    void deleteCollection(Long id);

    List<CollectionEntity> findAllCollectionByUserId(Long id);

    Optional<?> updateCollection(CollectionEntity collection);
}
