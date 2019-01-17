package com.promise.apps.server.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The request payload for adding a server.
 *
 */
@Data
@NoArgsConstructor
public class AddServerRequestV1
{
    public String address;
    public String username;
    public String password;
}
