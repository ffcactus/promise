package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.AssetInfoV1;

/**
 * Represents all kinds of asset information. If 2 resources have different
 * asset information they should have different <tt>id<tt> property.
 */
public class AssetInfo {
    public String manufacturer;
    public String serialNumber;
    public String partNumber;
    public String assertTag;
    public String sku;
    public String uuid;
    public String model;

    /**
     * Convert the model to response DTO.
     *
     * @param model model object.
     * @return The response DTO.
     */
    public static AssetInfoV1 toResponse(AssetInfo model) {
        var ret = new AssetInfoV1();
        ret.manufacturer = model.manufacturer;
        ret.serialNumber = model.serialNumber;
        ret.partNumber = model.partNumber;
        ret.assertTag = model.assertTag;
        ret.sku = model.sku;
        ret.uuid = model.uuid;
        ret.model = model.model;
        return ret;
    }
}
