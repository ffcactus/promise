package com.promise.platform.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents a session created by using username and password.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SessionInfo {
    @Id
    private String id;
    private Long userId;


}
