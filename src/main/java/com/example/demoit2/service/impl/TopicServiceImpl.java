package com.example.demoit2.service.impl;

import com.example.demoit2.entity.collection.TopicEntity;
import com.example.demoit2.repository.TopicRepository;
import com.example.demoit2.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public Optional<?> saveTopic(TopicEntity topic) {
        log.info("Saving new topic:{} to database", topic.getTopicName());
        return Optional.of(topicRepository.save(topic));
    }

    @Override
    public Optional<?> getTopics() {
        log.info("Getting all topics");
        return Optional.of(topicRepository.findAll());
    }

    @Override
    public void deleteTopic(Long id) {
        log.info("Topic with id:{} deleted from the database", id);
        topicRepository.deleteById(id);
    }
}
