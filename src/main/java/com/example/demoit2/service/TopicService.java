package com.example.demoit2.service;

import com.example.demoit2.entity.collection.TopicEntity;

import java.util.Optional;

public interface TopicService {

    Optional<?> saveTopic(TopicEntity topic);

    Optional<?> getTopics();

    void deleteTopic(Long id);
}
