package com.example.demoit2.repository;

import com.example.demoit2.entity.items.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long>{
    void deleteByUserIdAndItemId(Long userId, Long itemId);
}
