package com.promise.platform.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents the GET response to the
 */
@Data
@NoArgsConstructor
public class BasicResourceResponseV1 {
    public String id;
    public String uri;
    public String category;
    public String name;
    public Date createdAt;
    public Date updatedAt;
}
