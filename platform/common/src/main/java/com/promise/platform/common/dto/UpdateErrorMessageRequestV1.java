package com.promise.platform.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateErrorMessageRequestV1 {
    public String errorCode;
    public String message;
    public List<String> messageArgs;

    /**
     * Represents the common collection response.
     *
     * @param <T> The type of each element.
     */
    @Data
    @NoArgsConstructor
    public static class CollectionResponseV1<T> {
        public long pageIndex;
        public long pageSize;
        public Boolean hasNext;
        public Boolean hasPrevious;
        public String nextPageUri;
        public String previousPageUri;
        public long total;
        public Sort.Direction order;
        public String orderBy;
        public List<T> members;
    }
}
