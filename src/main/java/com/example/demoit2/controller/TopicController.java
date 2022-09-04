package com.example.demoit2.controller;

import com.example.demoit2.entity.collection.TopicEntity;
import com.example.demoit2.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@Slf4j
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<?> getAllTopics() {
        return ResponseEntity.ok().body(topicService.getTopics());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTopic(@RequestBody TopicEntity topic) {
        return ResponseEntity.ok().body(topicService.saveTopic(topic));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTopic(@RequestParam Long id) {
        try {
            topicService.deleteTopic(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Topic related to the Collection and cannot be deleted");
        }
    }
}
