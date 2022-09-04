package com.example.demoit2.repository;

import com.example.demoit2.entity.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    void deleteAllByIdIn(List<Long> ids);

    void findAllByIdIn(List<Long> ids);
}
