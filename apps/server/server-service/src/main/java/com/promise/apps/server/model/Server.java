package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.*;
import com.promise.platform.sdk.model.AssetInfo;
import com.promise.platform.sdk.model.ScopedResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Represents the server object.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Server extends ScopedResource {
    public String description;
    public String type;
    public ServerStateV1 state;
    public ServerHealthV1 health;
    // hardware state and health coming from the hardware only.
    public String hardwareState;
    public String hardwareHealth;
    public String powerState;
    public AssetInfo assetInfo;
    public ManagerSummary managerSummary;
    public BiosSummary biosSummary;
    public ProcessorSummary processorSummary;
    public MemorySummary memorySummary;
    public StorageSummary storageSummary;
    public PcieSummary pcieSummary;
    public AdapterSummary adapterSummary;
    public BoardSummary boardSummary;
    public PowerSummary powerSummary;
    public ThermalSummary thermalSummary;
    public FirmwareSummary firmwareSummary;
    public String serverHardwareTypeUri;
    public String serverProfileUri;
    public String taskUri;
    public String processorUri;
    public String biosUri;
    public String managerUri;
    public String boardUri;
    public String storageUri;
    public String pcieDeviceUri;
    public String networkAdapterUri;
    public String powerUri;
    public String thermalUri;
    public String firmwareUri;

    public Server() {
        assetInfo = new AssetInfo();
        managerSummary = new ManagerSummary();
        biosSummary = new BiosSummary();
        processorSummary = new ProcessorSummary();
        memorySummary = new MemorySummary();
        storageSummary = new StorageSummary();
        pcieSummary = new PcieSummary();
        adapterSummary = new AdapterSummary();
        boardSummary = new BoardSummary();
        powerSummary = new PowerSummary();
        thermalSummary = new ThermalSummary();
        firmwareSummary = new FirmwareSummary();
    }

    /**
     * Create and initialize a {@link Server} with it's basic information.
     *
     * @param basicInfo The basic information of the server.
     * @return The server created and initialized.
     */
    public static Server initialize(ServerBasicInfo basicInfo) {
        var server = new Server();
        server.id = UUID.randomUUID().toString();
        server.createdAt = new Date();
        server.updatedAt = server.createdAt;
        server.assetInfo = basicInfo.assetInfo;
        server.type = basicInfo.type;
        return server;
    }

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static GetServerResponseV1 toResponse(Server model) {
        var ret = new GetServerResponseV1();
        // for ScopedResource properties
        ret.id = model.id;
        ret.uri = model.uri;
        ret.name = model.name;
        ret.createdAt = model.createdAt;
        ret.updatedAt = model.updatedAt;
        ret.scopes = model.scopes;
        ret.description = model.description;
        ret.type = model.type;
        ret.state = model.state;
        ret.health = model.health;
        ret.hardwareState = model.hardwareState;
        ret.hardwareHealth = model.hardwareHealth;
        ret.powerState = model.powerState;
        ret.assetInfo = AssetInfo.toResponse(model.assetInfo);
        ret.processorSummary = ProcessorSummary.toResponse(model.processorSummary);
        ret.memorySummary = MemorySummary.toResponse(model.memorySummary);
        ret.storageSummary = StorageSummary.toResponse(model.storageSummary);
        ret.pcieSummary = PcieSummary.toResponse(model.pcieSummary);
        ret.adapterSummary = AdapterSummary.toResponse(model.adapterSummary);
        ret.powerSummary = PowerSummary.toResponse(model.powerSummary);
        ret.thermalSummary = ThermalSummary.toResponse(model.thermalSummary);
        ret.firmwareSummary = FirmwareSummary.toResponse(model.firmwareSummary);
        ret.serverHardwareTypeUri = model.serverHardwareTypeUri;
        ret.serverProfileUri = model.serverProfileUri;
        ret.taskUri = model.taskUri;
        return ret;
    }

    /**
     * The summary of {@link ManagerResource}
     */
    @Data
    @NoArgsConstructor
    public static class ManagerSummary {
        public List<String> bootOrder;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static ManagerSummaryV1 toResponse(ManagerSummary model) {
            var ret = new ManagerSummaryV1();
            return ret;
        }
    }

    /**
     * The summary of {@link BiosResource}
     */
    @Data
    @NoArgsConstructor
    public static class BiosSummary {
        public List<String> bootOrder;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static BiosSummaryV1 toResponse(BiosSummary model) {
            var ret = new BiosSummaryV1();
            ret.bootOrder = model.bootOrder;
            return ret;
        }
    }

    /**
     * The summary of {@link ProcessorResource}
     */
    @Data
    @NoArgsConstructor
    public static class ProcessorSummary {
        public Integer count;
        public String model;
        public HardwareStatusV1 status;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static ProcessorSummaryV1 toResponse(ProcessorSummary model) {
            var ret = new ProcessorSummaryV1();
            ret.count = model.count;
            ret.model = model.model;
            ret.status = model.status;
            return ret;
        }
    }

    /**
     * The summary of {@link MemoryResource}
     */
    @Data
    @NoArgsConstructor
    public static class MemorySummary {
        public HardwareStatusV1 status;
        public Integer totalSystemMemoryGiB;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static MemorySummaryV1 toResponse(MemorySummary model) {
            var ret = new MemorySummaryV1();
            ret.totalSystemMemoryGiB = model.totalSystemMemoryGiB;
            ret.status = model.status;
            return ret;
        }
    }

    /**
     * The summary of {@link StorageResource}
     */
    @Data
    @NoArgsConstructor
    public static class StorageSummary {
        public Integer driveCount;
        public List<String> raidDevice;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static StorageSummaryV1 toResponse(StorageSummary model) {
            var ret = new StorageSummaryV1();
            ret.driveCount = model.driveCount;
            return ret;
        }
    }

    /**
     * The summary of {@link PcieResource}
     */
    @Data
    @NoArgsConstructor
    public static class PcieSummary {
        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static PcieSummaryV1 toResponse(PcieSummary model) {
            var ret = new PcieSummaryV1();
            return ret;
        }
    }

    /**
     * The summary of {@link AdapterResource}
     */
    @Data
    @NoArgsConstructor
    public static class AdapterSummary {
        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static AdapterSummaryV1 toResponse(AdapterSummary model) {
            var ret = new AdapterSummaryV1();
            return ret;
        }
    }

    /**
     * The summary of {@link BoardResource}
     */
    @Data
    @NoArgsConstructor
    public static class BoardSummary {
        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static BoardSummaryV1 toResponse(BoardSummary model) {
            var ret = new BoardSummaryV1();
            return ret;
        }
    }

    /**
     * The summary of {@link PowerResource}
     */
    @Data
    @NoArgsConstructor
    public static class PowerSummary {
        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static PowerSummaryV1 toResponse(PowerSummary model) {
            var ret = new PowerSummaryV1();
            return ret;
        }
    }

    /**
     * The summary of {@link ThermalResource}
     */
    @Data
    @NoArgsConstructor
    public static class ThermalSummary {
        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static ThermalSummaryV1 toResponse(ThermalSummary model) {
            var ret = new ThermalSummaryV1();
            return ret;
        }
    }

    /**
     * The summary of {@link FirmwareResource}
     */
    @Data
    @NoArgsConstructor
    public static class FirmwareSummary {
        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static FirmwareSummaryV1 toResponse(FirmwareSummary model) {
            var ret = new FirmwareSummaryV1();
            return ret;
        }
    }

}
