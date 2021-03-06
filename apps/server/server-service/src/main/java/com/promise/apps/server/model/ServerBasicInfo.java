package com.promise.apps.server.model;

import lombok.Data;

/**
 * The basic information about a server. It should be able to be used to distinguish servers.
 */
@Data
public class ServerBasicInfo {
    public AssetInfo assetInfo;
    public String type;

    public ServerBasicInfo() {
        assetInfo = new AssetInfo();
    }
}
