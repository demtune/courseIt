package com.example.demoit2.service.impl;

import com.example.demoit2.entity.items.CommentEntity;
import com.example.demoit2.exception.CommentNotFoundException;
import com.example.demoit2.repository.CommentRepository;
import com.example.demoit2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Optional<CommentEntity> findCommentById(Long id) {
        log.info("Comment with id:{}", id);
        return commentRepository.findById(id);
    }

    @Override
    public List<CommentEntity> findAllCommentByItemId(Long id) {
        log.info("Comments with item id:{}", id);
        return commentRepository.findAllByItemId(id);
    }

    @Override
    public CommentEntity saveComment(CommentEntity comment) {
        log.info("Saving new comment:{} to database", comment.getId());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        log.info("Comment with id:{} deleted", id);
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<CommentEntity> updateComment(CommentEntity comment) {
        commentRepository.findById(comment.getId())
                .orElseThrow(() -> new CommentNotFoundException("Comment not found in database"));
        return Optional.of(this.saveComment(comment));
    }
}
