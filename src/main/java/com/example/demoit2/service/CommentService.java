package com.example.demoit2.service;

import com.example.demoit2.entity.items.CommentEntity;

import java.util.Optional;

public interface CommentService {
    Optional<?> findCommentById(Long id);

    Optional<?> findAllCommentByItemId(Long id);

    Optional<?> saveComment(CommentEntity comment);

    void deleteComment(Long id);

    Optional<?> updateComment(CommentEntity comment);
}
