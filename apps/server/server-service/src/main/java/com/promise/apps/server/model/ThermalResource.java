package com.promise.apps.server.model;

import com.promise.apps.server.model.Server.ThermalSummary;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.promise.apps.server.sdk.dto.ThermalResourceV1;
import com.promise.apps.server.sdk.dto.ThermalResourceV1.FanV1;
import com.promise.platform.common.model.BasicResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the thermal resource of a server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ThermalResource extends BasicResource {
    public ThermalSummary summary;
    public List<Fan> fans;

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static ThermalResourceV1 toResponse(ThermalResource model) {
        var ret = new ThermalResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summary = ThermalSummary.toResponse(model.summary);
        ret.fans = model.fans.stream().map(Fan::toResponse).collect(Collectors.toList());
        return ret;
    }

    /**
     * Represents the fan device on the server.
     */
    @Data
    @NoArgsConstructor
    public static class Fan {
        public String index;
        public HardwareStatusV1 hardwareStatus;
        public AssetInfo assetInfo;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static FanV1 toResponse(Fan model) {
            var ret = new FanV1();
            ret.index = model.index;
            ret.hardwareStatus = model.hardwareStatus;
            ret.assetInfo = AssetInfo.toResponse(model.assetInfo);
            return ret;
        }
    }

}
