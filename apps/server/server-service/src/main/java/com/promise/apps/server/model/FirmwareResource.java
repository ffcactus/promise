package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.FirmwareResourceV1;
import com.promise.apps.server.sdk.dto.FirmwareResourceV1.FirmwareV1;
import com.promise.platform.sdk.model.BasicResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FirmwareResource extends BasicResource {
    public Server.FirmwareSummary summary;
    public List<Firmware> firmwares;

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static FirmwareResourceV1 toResponse(FirmwareResource model) {
        var ret = new FirmwareResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summay = Server.FirmwareSummary.toResponse(model.summary);
        ret.firmwares = model.firmwares.stream().map(Firmware::toResponse).collect(Collectors.toList());
        return ret;
    }

    /**
     * Represents firmware in {@link FirmwareResource}
     */
    @Data
    @NoArgsConstructor
    public static class Firmware {
        public String name;
        public String version;
        public Boolean updateable;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static FirmwareV1 toResponse(Firmware model) {
            var ret = new FirmwareV1();
            ret.name = model.name;
            ret.version = model.version;
            ret.updateable = model.updateable;
            return ret;
        }
    }

}
