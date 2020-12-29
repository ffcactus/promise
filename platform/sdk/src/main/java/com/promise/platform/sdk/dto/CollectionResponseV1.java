package com.promise.platform.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

/**
 * Represents the common collection response.
 *
 * @param <T> The type of each element.
 */
@Data
@NoArgsConstructor
public class CollectionResponseV1<T> {
    public long pageIndex;
    public long pageSize;
    public Boolean hasNext;
    public Boolean hasPrevious;
    public String nextPageUri;
    public String previousPageUri;
    public long total;
    public Direction order;
    public String orderBy;
    public List<T> members;
}
