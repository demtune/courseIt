package com.example.demoit2.service;

import com.example.demoit2.entity.collection.TopicEntity;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    TopicEntity saveTopic(TopicEntity topic);

    List<TopicEntity> getTopics();

    void deleteTopic(Long id);
}
