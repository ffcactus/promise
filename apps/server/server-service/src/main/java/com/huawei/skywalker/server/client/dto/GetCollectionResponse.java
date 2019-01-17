package com.huawei.skywalker.server.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of collection response from server's Redfish API.
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCollectionResponse
{
    @JsonProperty("Members@odata.count")
    public Integer count;

    public List<OdataId> members;
}
