package com.huawei.skywalker.server.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huawei.skywalker.server.model.ThermalResource.Fan;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of thermal from server's Redfish API.
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetThermalResponse
{
    public List<FanResponse> fans;

    /**
     * Represents the response of fan from server's Redfish API.
     *
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FanResponse
    {
        public String memberId;
        public String name;
        public HardwareStatusV1 status;
        public String partNumber;
        
        /**
         * Convert the response to model.
         * 
         * @return The model.
         */
        @JsonIgnore
        public Fan convert()
        {
            var ret = new Fan();
//            ret.memberId = this.memberId;
//            ret.name = this.name;
//            ret.status = this.status;
//            ret.partNumber = this.partNumber;
            return ret;
        }
    }
}
