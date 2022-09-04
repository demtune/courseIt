package com.example.demoit2.controller;

import com.example.demoit2.entity.items.CommentEntity;
import com.example.demoit2.exception.CommentNotFoundException;
import com.example.demoit2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getCommentById(@RequestParam Long id) {
        return ResponseEntity.ok().body(commentService.findCommentById(id));
    }

    @GetMapping("/item")
    public ResponseEntity<?> getCommentByItemId(@RequestParam Long id) {
        return ResponseEntity.ok().body(commentService.findAllCommentByItemId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveComment(@RequestBody CommentEntity comment) {
        return ResponseEntity.ok().body(commentService.saveComment(comment));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editComment(@RequestBody CommentEntity comment) {
        try {
            return ResponseEntity.ok().body((commentService.updateComment(comment)));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Exception when call editCollection: ", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
