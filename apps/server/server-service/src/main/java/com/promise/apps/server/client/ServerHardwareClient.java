package com.promise.apps.server.client;

import com.promise.apps.server.model.*;
import com.sun.istack.NotNull;

/**
 * The server hardware client interface that a server hardware vendor should
 * provide.
 */
public interface ServerHardwareClient {
    /**
     * Get server basic information.
     *
     * @return The {@link ServerBasicInfo}.
     */
    @NotNull
    public ServerBasicInfo getServerBasicInfo();

    /**
     * Get the {@link ProcessorResource} of the server.
     *
     * @return The {@link ProcessorResource} of the server.
     */
    @NotNull
    public ProcessorResource getProcessorResource();

    /**
     * Get the {@link MemoryResource} of the server.
     *
     * @return The {@link MemoryResource} of the server.
     */
    @NotNull
    public MemoryResource getMemoryResource();

    /**
     * Get the {@link StorageResource} of the server.
     *
     * @return The {@link StorageResource} of the server.
     */
    @NotNull
    public StorageResource getStorageResource();

    /**
     * Get the {@link BiosResource} of the server.
     *
     * @return The {@link BiosResource} of the server.
     */
    @NotNull
    public BiosResource getBiosResource();

    /**
     * Get the {@link ManagerResource} of the server.
     *
     * @return The {@link ManagerResource} of the server.
     */
    @NotNull
    public ManagerResource getManagerResource();

    /**
     * Get the {@link BoardResource} of the server.
     *
     * @return The {@link BoardResource} of the server.
     */
    @NotNull
    public BoardResource getBoardResource();

    /**
     * Get the {@link PcieResource} of the server.
     *
     * @return The {@link PcieResource} of the server.
     */
    @NotNull
    public PcieResource getPcieResource();

    /**
     * Get the {@link AdapterResource} of the server.
     *
     * @return The {@link AdapterResource} of the server.
     */
    @NotNull
    public AdapterResource getAdapterResource();

    /**
     * Get the {@link PowerResource} of the server.
     *
     * @return The {@link PowerResource} of the server.
     */
    @NotNull
    public PowerResource getPowerResource();

    /**
     * Get the {@link ThermalResource} of the server.
     *
     * @return The {@link ThermalResource} of the server.
     */
    @NotNull
    public ThermalResource getThermalResource();

    /**
     * Get the {@link FirmwareResource} of the server.
     *
     * @return The {@link FirmwareResource} of the server.
     */
    @NotNull
    public FirmwareResource getFirmwareResource();

}
