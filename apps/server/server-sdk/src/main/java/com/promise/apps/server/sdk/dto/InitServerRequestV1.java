package com.promise.apps.server.sdk.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class defines the properties that is needed for the system to connect to
 * the server while the server making a connection.
 */
@Data
@RequiredArgsConstructor
public class InitServerRequestV1 {
    public final String serialNumber;
    public final String username;
    public final String password;
    public String name;
    public String description;
}
