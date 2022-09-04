package com.example.demoit2.controller;

import com.example.demoit2.entity.items.TagEntity;
import com.example.demoit2.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTags() {
        return ResponseEntity.ok().body(tagService.findAllTags());
    }

    @GetMapping
    public ResponseEntity<?> getTagById(@RequestParam Long id) {
        return ResponseEntity.ok().body(tagService.findTagById(id));

    }

    @GetMapping("/findByNameLike")
    public ResponseEntity<?> getAllTagsByName(@RequestParam String name) {
        return ResponseEntity.ok().body(tagService.findAllTagsByName(name));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTag(@RequestBody TagEntity tag) {
        return ResponseEntity.ok().body(tagService.saveTag(tag));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTagToItem(@RequestParam String tagName, @RequestParam Long itemId) {
        tagService.addTagToItem(tagName, itemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTag(@RequestParam Long id) {
        try {
            tagService.deleteTag(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tag related to the Item and cannot be deleted");
        }
    }
}
