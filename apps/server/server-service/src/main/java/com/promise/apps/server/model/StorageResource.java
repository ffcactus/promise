package com.promise.apps.server.model;

import com.promise.apps.server.model.Server.StorageSummary;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.promise.apps.server.sdk.dto.StorageResourceV1;
import com.promise.apps.server.sdk.dto.StorageResourceV1.StorageV1;
import com.promise.apps.server.sdk.dto.StorageResourceV1.StorageV1.DriveV1;
import com.promise.apps.server.sdk.dto.StorageResourceV1.StorageV1.StorageSettingsV1;
import com.promise.platform.sdk.model.AssetInfo;
import com.promise.platform.sdk.model.BasicResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the storage resource of a server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StorageResource extends BasicResource {
    public StorageSummary summary;
    public List<Storage> storages;

    public StorageResource() {
        summary = new StorageSummary();
        storages = new ArrayList<Storage>();
    }

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static StorageResourceV1 toResponse(StorageResource model) {
        var ret = new StorageResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summary = StorageSummary.toResponse(model.summary);
        ret.storages = model.storages.stream().map(Storage::toResponse).collect(Collectors.toList());
        return ret;
    }

    /**
     * Represents the response of storage.
     */
    @Data
    @NoArgsConstructor
    public static class Storage {
        public AssetInfo assetInfo;
        public HardwareStatusV1 hardwareStatus;
        public Integer speedGbps;
        public String firmwareVersion;
        public List<String> supportedDeviceProtocols;
        public List<Drive> drives;
        public StorageSettings settings;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static StorageV1 toResponse(Storage model) {
            var ret = new StorageV1();
            ret.assetInfo = AssetInfo.toResponse(model.assetInfo);
            ret.hardwareStatus = model.hardwareStatus;
            ret.speedGbps = model.speedGbps;
            ret.firmwareVersion = model.firmwareVersion;
            ret.supportedDeviceProtocols = model.supportedDeviceProtocols;
            ret.drives = model.drives.stream().map(Drive::toResponse).collect(Collectors.toList());
            ret.settings = StorageSettings.toResponse(model.settings);
            return ret;
        }

        /**
         * Represents the hardware drive in {@link Storage}
         */
        public static class Drive {
            public AssetInfo assetInfo;
            public Integer driveId;
            public String name;
            public String revision;
            public HardwareStatusV1 status;
            public Long capacityBytes;
            public Boolean failurePredicted;
            public String protocol;
            public String mediaType;
            public Integer capableSpeedGbs;
            public Integer negotiatedSpeedGbs;
            public Integer predictedMediaLifeLeftPercent;
            public String hotspareType;

            /**
             * Convert model to response DTO.
             *
             * @param model The model object.
             * @return The response DTO object.
             */
            public static DriveV1 toResponse(Drive model) {
                var ret = new DriveV1();
                ret.assetInfo = AssetInfo.toResponse(model.assetInfo);
                ret.driveId = model.driveId;
                ret.name = model.name;
                ret.revision = model.revision;
                ret.status = model.status;
                ret.capacityBytes = model.capacityBytes;
                ret.failurePredicted = model.failurePredicted;
                ret.protocol = model.protocol;
                ret.mediaType = model.mediaType;
                ret.capableSpeedGbs = model.capableSpeedGbs;
                ret.negotiatedSpeedGbs = model.negotiatedSpeedGbs;
                ret.predictedMediaLifeLeftPercent = model.predictedMediaLifeLeftPercent;
                ret.hotspareType = model.hotspareType;
                return ret;
            }
        }

        /**
         * Represents the storage settings in {@link Storage}
         */
        public static class StorageSettings {
            /**
             * Convert model to response DTO.
             *
             * @param model The model object.
             * @return The response DTO object.
             */
            public static StorageSettingsV1 toResponse(StorageSettings model) {
                var ret = new StorageSettingsV1();
                return ret;
            }
        }
    }
}
