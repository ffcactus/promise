package com.promise.platform.sdk.dto;

/**
 * Different kind of things can be taken as a scope, for example, a company, a
 * department, a data center, a enclosure and so on, or a logical one that is
 * created by the user, that why a scope has both
 * the category and it's URI.
 */
public class Scope {
    public String category;
    public String uri;
}
