package com.promise.platform.devicebasic.repository;

import com.promise.platform.devicebasic.entity.CollectorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CollectorRepository extends PagingAndSortingRepository<CollectorEntity, String> {
}
