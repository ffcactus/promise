package com.promise.platform.devicebasic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CollectorGroup {
    private String id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
