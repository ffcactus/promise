package com.promise.platform.devicebasic.repository;

import com.promise.platform.devicebasic.entity.DgCgMapping;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DgCgMappingRepository extends PagingAndSortingRepository<DgCgMapping, Long> {
    DgCgMapping findByLeftId(Long left);
    DgCgMapping findByRightId(Long left);
}
