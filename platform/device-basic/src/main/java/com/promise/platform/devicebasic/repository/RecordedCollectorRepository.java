package com.promise.platform.devicebasic.repository;

import com.promise.platform.devicebasic.entity.CollectorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RecordedCollectorRepository extends PagingAndSortingRepository<CollectorEntity, Long> {
    Optional<CollectorEntity> getFirstBySn(String sn);
}
