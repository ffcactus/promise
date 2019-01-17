package com.huawei.skywalker.server.strategy;

import com.huawei.skywalker.server.client.ServerHardwareClient;
import com.huawei.skywalker.server.model.Server;

/**
 * Includes all the main step of adding a server.
 *
 */
public interface ServerAddStrategy
{
    /**
     * Refresh the server information expect for all kinds of dedicated resource
     * and their summary.
     * 
     * @param id The server ID.
     * @return The updated server.
     */
    public Server refreshServer(String id);

    /**
     * Refresh manager resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshManagerResource(String id, ServerHardwareClient client);

    /**
     * Refresh processor resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshProcessorResource(String id, ServerHardwareClient client);

    /**
     * Refresh memory resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshMemoryResource(String id, ServerHardwareClient client);

    /**
     * Refresh storage resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshStorageResource(String id, ServerHardwareClient client);

    /**
     * Refresh BIOS resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshBiosResource(String id, ServerHardwareClient client);

    /**
     * Refresh board resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshBoardResource(String id, ServerHardwareClient client);

    /**
     * Refresh PCIe resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshPcieResource(String id, ServerHardwareClient client);

    /**
     * Refresh adapter resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshAdapterResource(String id, ServerHardwareClient client);

    /**
     * Refresh power resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshPowerResource(String id, ServerHardwareClient client);

    /**
     * Refresh thermal resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshThermalResource(String id, ServerHardwareClient client);

    /**
     * Refresh firmware resource and it's summary.
     * 
     * @param id The server ID.
     * @param client The {@link ServerHardwareClient}
     * @return The updated server.
     */
    public Server refreshFirmwareResource(String id, ServerHardwareClient client);
}
