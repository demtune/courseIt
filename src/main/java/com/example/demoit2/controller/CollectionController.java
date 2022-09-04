package com.example.demoit2.controller;

import com.example.demoit2.entity.collection.CollectionEntity;
import com.example.demoit2.exception.CollectionNotFoundException;
import com.example.demoit2.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
@Slf4j
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public ResponseEntity<?> getAllCollections() {
        return ResponseEntity.ok().body(collectionService.getCollections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCollectionById(@PathVariable Long id) {
        return ResponseEntity.ok().body(collectionService.findCollectionById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCollection(@RequestBody CollectionEntity collection) {
        return ResponseEntity.ok().body(collectionService.saveCollection(collection));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCollection(@RequestParam Long id) {
        collectionService.deleteCollection(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCollecionsByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok().body(collectionService.findAllCollectionByUserId(userId));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCollection(@RequestBody CollectionEntity collection) {
        try {
            return ResponseEntity.ok().body((collectionService.updateCollection(collection)));
        } catch (CollectionNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Exception when call editCollection: ", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
