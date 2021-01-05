package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.BiosResourceV1;
import com.promise.platform.common.model.BasicResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Represents the BIOS resource of a server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BiosResource extends BasicResource {
    public Server.BiosSummary summary;
    public Map<String, Object> attributes;

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static BiosResourceV1 toResponse(BiosResource model) {
        var ret = new BiosResourceV1();
        ret.summary = Server.BiosSummary.toResponse(model.summary);
        ret.attributes = model.attributes;
        return ret;
    }
}
