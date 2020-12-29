package com.promise.platform.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseV1<T> {
    private int page;
    private int size;
    private long total;
    private SortDirection sortDirection;
    private String sortBy;
    private List<T> items;
}
