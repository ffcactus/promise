package com.huawei.skywalker.server.model;

import java.util.Map;

import com.huawei.skywalker.server.model.Server.BiosSummary;
import com.promise.apps.server.sdk.dto.BiosResourceV1;
import com.promise.platform.sdk.model.BasicResource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the BIOS resource of a server.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BiosResource extends BasicResource
{
    public BiosSummary summary;
    public Map<String, Object> attributes;
    
    /**
     * Convert model to response DTO.
     * 
     * @param model The model object.
     * @return The response DTO object.
     */
    public static BiosResourceV1 toResponse(BiosResource model) {
        var ret = new BiosResourceV1();
        ret.summary = BiosSummary.toResponse(model.summary);
        ret.attributes = model.attributes;
        return ret;
    }
}
