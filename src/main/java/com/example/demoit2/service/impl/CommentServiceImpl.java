package com.example.demoit2.service.impl;

import com.example.demoit2.entity.items.CommentEntity;
import com.example.demoit2.exception.CommentNotFoundException;
import com.example.demoit2.exception.ItemNotFoundException;
import com.example.demoit2.repository.CommentRepository;
import com.example.demoit2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Optional<?> findCommentById(Long id) {
        log.info("Comment with id:{}", id);
        return Optional.of(commentRepository.findById(id));
    }

    @Override
    public Optional<?> findAllCommentByItemId(Long id) {
        log.info("Comments with item id:{}", id);
        return Optional.of(commentRepository.findAllByItemId(id));
    }

    @Override
    public Optional<?> saveComment(CommentEntity comment) {
        log.info("Saving new comment:{} to database", comment.getId());
        return Optional.of(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long id) {
        log.info("Comment with id:{} deleted", id);
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<?> updateComment(CommentEntity comment) {
        commentRepository.findById(comment.getId())
                .orElseThrow(() -> new CommentNotFoundException("Comment not found in database"));
        return Optional.of(this.saveComment(comment));
    }
}
