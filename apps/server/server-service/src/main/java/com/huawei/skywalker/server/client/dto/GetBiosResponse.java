package com.huawei.skywalker.server.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huawei.skywalker.server.model.BiosResource;

/**
 * Represents the response of BIOS from server's Redfish API.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBiosResponse extends BiosResource
{
    /**
     * Convert the response to model.
     * @return The model.
     */
    @JsonIgnore
    public BiosResource convert()
    {
        BiosResource model = new BiosResource();
        model.attributes = this.attributes;
        return model;
    }
}
