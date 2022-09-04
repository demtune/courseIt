package com.example.demoit2.controller;

import com.example.demoit2.entity.items.LikeEntity;
import com.example.demoit2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/plus")
    public ResponseEntity<?> like(@RequestBody LikeEntity like) {
        try {
            var likeItem = likeService.addLike(like);
            return ResponseEntity.ok().body(likeItem);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("The user cannot put more than one Like");
        }

    }

    @PostMapping("/minus")
    public ResponseEntity<?> unLike(@RequestParam Long userId, @RequestParam Long itemId) {
        likeService.removeLike(userId, itemId);
        return ResponseEntity.ok().build();
    }
}
