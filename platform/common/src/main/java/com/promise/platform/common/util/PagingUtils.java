package com.promise.platform.common.util;

import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.common.dto.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The utilities about paging.
 */
public class PagingUtils {

    /**
     * Convert from {@link ListRequestV1} to {@link Pageable}
     *
     * @param request {@link ListRequestV1}
     * @return {@link Pageable}
     */
    public static Pageable from(ListRequestV1 request) {
        var sortDirection = request.getSortDirection();
        var sortBy = request.getSortBy();
        Sort sort;
        if (sortDirection == null || sortBy.isEmpty()) {
            sort = Sort.unsorted();
        } else {
            sort = Sort.by(
                    request.getSortDirection() == SortDirection.Asc ? Sort.Direction.ASC : Sort.Direction.DESC,
                    request.getSortBy()
            );
        }
        return PageRequest.of(request.getPage(), request.getSize(), sort);
    }

    /**
     * Convert the {@link Page} result to {@link ListResponseV1}
     *
     * @param page the page result.
     * @param f    the function that convert the element in {@link Page} to element in {@link ListResponseV1}
     * @param <T>  the element type in {@link Page}
     * @param <R>  the element type in {@link ListResponseV1}
     * @return {@link ListResponseV1}
     */
    public static <T, R> ListResponseV1<R> from(Page<T> page, Function<T, R> f) {
        var pageable = page.getPageable();
        var ret = new ListResponseV1<R>();
        ret.setPage(pageable.getPageNumber());
        ret.setSize(pageable.getPageNumber());
        ret.setSortDirection(SortDirection.Asc); // TODO
        ret.setTotal(page.getTotalElements());
        ret.setItems(page.stream().map(f).collect(Collectors.toList()));
        return ret;
    }
}
