package com.promise.platform.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents the resource that is managed by this platform.
 */
@Data
@NoArgsConstructor
public class Resource {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    /**
     * Copy resources.
     *
     * @param to   from which
     * @param from to which
     * @param <R>  the resource type.
     */
    public static <R extends Resource, T extends Resource> void copy(R to, T from) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        to.setCreateAt(from.getCreateAt());
        to.setUpdateAt(from.getUpdateAt());
    }
}

