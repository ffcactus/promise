package com.promise.platform.common.dto;

import lombok.Data;

@Data
public class ListRequestV1 {
    private int page;
    private int size;
    private SortDirection sortDirection;
    private String sortBy;

    public ListRequestV1(String page, String size, String sortDirection, String sortBy) {
        this.page = intOrDefault(page, 0);
        this.size = intOrDefault(size, -1);
        if (SortDirection.Asc.name().equalsIgnoreCase(sortDirection)) {
            this.sortDirection = SortDirection.Asc;
        } else if (SortDirection.Desc.name().equalsIgnoreCase(sortDirection)) {
            this.sortDirection = SortDirection.Desc;
        } else {
            this.sortDirection = null;
        }
        this.sortBy = sortBy;
    }

    private static int intOrDefault(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
