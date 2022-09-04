package com.example.demoit2.repository;

import com.example.demoit2.entity.collection.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
    List<CollectionEntity> findAllByUserId(Long id);
}
