package com.example.demoit2.service.impl;

import com.example.demoit2.entity.items.LikeEntity;
import com.example.demoit2.repository.LikeRepository;
import com.example.demoit2.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public Optional<?> addLike(LikeEntity like) {
        log.info("Like for item:{}", like.getItem().getId());
        return Optional.of(likeRepository.save(like));
    }

    @Override
    @Transactional
    public void removeLike(Long userId, Long itemId) {

        if (userId == null && itemId == null) {
            log.error("Error dislike for item with id:{}", itemId);
        } else {
            log.info("User:{} unlike for item:{}", userId, itemId);
            likeRepository.deleteByUserIdAndItemId(userId, itemId);
        }
    }
}



