package com.promise.platform.auth.repository;

import com.promise.platform.auth.model.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionInfo, String> {

    void deleteByUserId(Long userId);

}
