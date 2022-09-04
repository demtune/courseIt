package com.example.demoit2.repository;

import com.example.demoit2.entity.items.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<?> findAllByItemId(Long id);

    Optional<?> findByTagName(String tagName);
}
