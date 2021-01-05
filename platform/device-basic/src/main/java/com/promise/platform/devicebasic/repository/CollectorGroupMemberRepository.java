package com.promise.platform.devicebasic.repository;

import com.promise.platform.devicebasic.entity.CollectorGroupMemberEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CollectorGroupMemberRepository extends PagingAndSortingRepository<CollectorGroupMemberEntity, Long> {
    CollectorGroupMemberEntity findFirstByLeftIdAndRightId(Long left, Long right);

    List<CollectorGroupMemberEntity> findByLeftId(Long left, Pageable pageable);

    List<CollectorGroupMemberEntity> findByLeftId(Long left);

    void deleteByRightId(Long right);
}
