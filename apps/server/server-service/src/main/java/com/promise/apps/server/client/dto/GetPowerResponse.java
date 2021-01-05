package com.promise.apps.server.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.promise.apps.server.model.PowerResource;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the response of power from server's Redfish API.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPowerResponse {
    List<PowerSupplyResponse> powerSupplies;

    /**
     * Represents the response of power supply from server's Redfish API.
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PowerSupplyResponse {
        public String memberId;
        public String name;
        public HardwareStatusV1 status;
        public String powerSupplyType;
        public Integer powerCapacityWatts;
        public String model;
        public String firmwareVersion;
        public String manufacturer;
        public String partNumber;

        /**
         * Convert the response to model.
         *
         * @return The model.
         */
        @JsonIgnore
        public PowerResource.PowerSupply convert() {
            var ret = new PowerResource.PowerSupply();
//            ret.memberId = this.memberId;
//            ret.name = this.name;
//            ret.status = this.status;
//            ret.powerSupplyType = this.powerSupplyType;
//            ret.powerCapacityWatts = this.powerCapacityWatts;
//            ret.model = this.model;
//            ret.firmwareVersion = this.firmwareVersion;
//            ret.manufacturer = this.manufacturer;
//            ret.partNumber = this.partNumber;
            return ret;
        }
    }
}
