package com.promise.platform.sdk.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the GET response to the
 * {@link com.promise.platform.sdk.model.BasicResource}
 *
 */
@Data
@NoArgsConstructor
public class BasicResourceResponseV1
{
    public String id;
    public String uri;
    public String category;
    public String name;
    public Date createdAt;
    public Date updatedAt;
}
