package com.promise.apps.server.sdk.dto;

import com.promise.platform.sdk.dto.AssetInfoV1;
import com.promise.platform.sdk.dto.BasicResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the response of storage resource.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class StorageResourceV1 extends BasicResourceResponseV1 {
    public StorageSummaryV1 summary;
    public List<StorageV1> storages;

    /**
     * Represents the response of storage.
     */
    @Data
    @NoArgsConstructor
    public static class StorageV1 {
        public AssetInfoV1 assetInfo;
        public HardwareStatusV1 hardwareStatus;
        public Integer speedGbps;
        public String firmwareVersion;
        public List<String> supportedDeviceProtocols;
        public List<DriveV1> drives;
        public StorageSettingsV1 settings;

        /**
         * Represents the hardware drive in {@link StorageV1}
         */
        public static class DriveV1 {
            public AssetInfoV1 assetInfo;
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
        }

        /**
         * Represents the storage settings in {@link StorageV1}
         */
        public static class StorageSettingsV1 {

        }
    }
}
