package com.example.demoit2.service.impl;

import com.example.demoit2.entity.collection.CollectionEntity;
import com.example.demoit2.exception.CollectionNotFoundException;
import com.example.demoit2.repository.CollectionRepository;
import com.example.demoit2.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Override
    public Optional<?> findCollectionById(Long id) {
        log.info("Collection with id:{}", id);
        return collectionRepository.findById(id);
    }

    @Override
    public Optional<?> saveCollection(CollectionEntity collection) {
        log.info("Saving new collection:{} to database", collection.getCollectionName());
        return Optional.of(collectionRepository.save(collection));
    }

    @Override
    public Optional<?> getCollections() {
        log.info("Getting all collections");
        return Optional.of(collectionRepository.findAll());
    }

    @Override
    public void deleteCollection(Long id) {
        log.info("Collection with id:{} deleted from the database", id);
        collectionRepository.deleteById(id);
    }

    @Override
    public List<CollectionEntity> findAllCollectionByUserId(Long id) {
        log.info("Collections by user id:{}", id);
        return collectionRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    public Optional<?> updateCollection(CollectionEntity collection) {
        collectionRepository.findById(collection.getId())
                .orElseThrow(() -> new CollectionNotFoundException("Collection not found in database"));
        log.info("Saving new collection:{} to database", collection.getCollectionName());
        return Optional.of(this.saveCollection(collection));
    }
}
