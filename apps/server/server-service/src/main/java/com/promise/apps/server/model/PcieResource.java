package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.PcieResourceV1;
import com.promise.apps.server.sdk.dto.PcieResourceV1.PcieDeviceV1;
import com.promise.platform.common.model.BasicResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the PCIe resource of a server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PcieResource extends BasicResource {
    public Server.PcieSummary summary;
    public List<PcieDevice> instances;

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static PcieResourceV1 toResponse(PcieResource model) {
        var ret = new PcieResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summary = Server.PcieSummary.toResponse(model.summary);
        ret.instances = model.instances.stream().map(PcieDevice::toResponse).collect(Collectors.toList());
        return ret;
    }

    /**
     * Represents the PCIe device.
     */
    public static class PcieDevice {
        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static PcieDeviceV1 toResponse(PcieDevice model) {
            var ret = new PcieDeviceV1();
            return ret;
        }
    }
}
