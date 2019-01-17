package com.huawei.skywalker.server.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of software inventory from server's Redfish API.
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSoftwareInventoryResponse
{
    public String id;
    public String name;
    public String version;
    public Boolean updateable;
    
    /**
     * Convert the response to model.
     * @return The model.
     */
//    @JsonIgnore
//    public SoftwareInventory convert() {
//        var ret = new SoftwareInventory();
//        ret.id = this.id;
//        ret.name = this.name;
//        ret.version = this.version;
//        ret.updateable = this.updateable;      
//        return ret;
//    }
}
