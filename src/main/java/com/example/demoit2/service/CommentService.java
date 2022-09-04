package com.example.demoit2.service;

import com.example.demoit2.entity.items.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentEntity> findCommentById(Long id);

    List<CommentEntity> findAllCommentByItemId(Long id);

    CommentEntity saveComment(CommentEntity comment);

    void deleteComment(Long id);

    Optional<CommentEntity> updateComment(CommentEntity comment);
}
