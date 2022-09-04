package com.example.demoit2.repository;

import com.example.demoit2.entity.collection.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    TopicEntity findByTopicName(String topicName);
}
