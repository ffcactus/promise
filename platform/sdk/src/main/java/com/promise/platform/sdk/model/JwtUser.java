package com.promise.platform.sdk.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class holds the information that will be included in JWT token.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class JwtUser {
    @NotNull
    protected Long id;
    @NotNull
    protected String username;
    //@NotNull
    protected String company;
    //@NotNull
    protected List<String> roles;
    //@NotNull
    protected List<String> organizations;
}