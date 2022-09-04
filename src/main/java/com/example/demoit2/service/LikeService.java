package com.example.demoit2.service;

import com.example.demoit2.entity.items.LikeEntity;

import java.util.Optional;

public interface LikeService {
    Optional<?> addLike(LikeEntity like);

    void removeLike(Long userId, Long itemId);
}
