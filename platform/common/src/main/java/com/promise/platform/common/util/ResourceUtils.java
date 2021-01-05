package com.promise.platform.common.util;

import com.promise.platform.common.Resource;
import com.promise.platform.common.entity.ResourceEntity;

/**
 * Contains utilities for resource operation.
 */
public class ResourceUtils {

    /**
     * Copy from entity to resource.
     *
     * @param to   from which
     * @param from to which
     * @param <R>  the resource type.
     * @param <T>  the entity type.
     */
    public static <R extends Resource, T extends ResourceEntity> void fromEntityToResource(R to, T from) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        to.setCreateAt(from.getCreateAt());
        to.setUpdateAt(from.getUpdateAt());
    }
}
