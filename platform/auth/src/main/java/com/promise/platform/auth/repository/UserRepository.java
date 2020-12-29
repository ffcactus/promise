package com.promise.platform.auth.repository;

import com.promise.platform.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String username);

    Optional<UserEntity> findByEmail(String email);
}
