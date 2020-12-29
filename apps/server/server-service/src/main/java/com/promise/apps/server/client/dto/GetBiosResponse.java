package com.promise.apps.server.client.dto;

import com.promise.apps.server.model.BiosResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the response of BIOS from server's Redfish API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBiosResponse extends BiosResource {
    /**
     * Convert the response to model.
     *
     * @return The model.
     */
    @JsonIgnore
    public BiosResource convert() {
        BiosResource model = new BiosResource();
        model.attributes = this.attributes;
        return model;
    }
}
