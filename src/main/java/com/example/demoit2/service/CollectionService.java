package com.example.demoit2.service;

import com.example.demoit2.entity.collection.CollectionEntity;

import java.util.List;
import java.util.Optional;

public interface CollectionService {
    Optional<CollectionEntity> findCollectionById(Long id);

    CollectionEntity saveCollection(CollectionEntity collection);

    List<CollectionEntity> getCollections();

    void deleteCollection(Long id);

    List<CollectionEntity> findAllCollectionByUserId(Long id);

    Optional<CollectionEntity> updateCollection(CollectionEntity collection);
}
