package com.promise.apps.server.service;

import com.promise.apps.server.client.HuaweiServerHardwareClient;
import com.promise.apps.server.context.AddServerContext;
import com.promise.apps.server.model.*;
import com.promise.apps.server.repository.*;
import com.promise.apps.server.sdk.dto.AddServerRequestV1;
import com.promise.apps.server.sdk.dto.InitServerRequestV1;
import com.promise.platform.common.exception.ResourceAlreadyExistException;
import com.promise.platform.sdk.model.AsynchResult;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService {
    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private ProcessorResourceRepository processorRepository;
    @Autowired
    private MemoryResourceRepository memoryRepository;
    @Autowired
    private StorageResourceRepository storageRepository;
    @Autowired
    private ManagerResourceRepository managerRepository;
    @Autowired
    private BiosResourceRepository biosRepository;
    @Autowired
    private BoardResourceRepository boardRepository;
    @Autowired
    private PcieResourceRepository pcieRepository;
    @Autowired
    private AdapterResourceRepository adapterRepository;
    @Autowired
    private PowerResourceRepository powerRepository;
    @Autowired
    private ThermalResourceRepository thermalRepository;
    @Autowired
    private FirmwareResourceRepository firmwareRepository;


    @NotNull
    public Server initServer(InitServerRequestV1 initServerRequest) {
        return new Server();
    }

    /**
     * Add a server by server address and credential.
     *
     * @param request The request includes the server's address and credential.
     * @return The task and the initialized server.
     */
    @NotNull
    public AsynchResult<Server> addServer(AddServerRequestV1 request) {
        // TODO 
        // We need first to probe the server to find out some server properties to decide what kind of client to use.
        var client = new HuaweiServerHardwareClient(request.address, request.username, request.password);
        var basicInfo = client.getServerBasicInfo();
        var currentServer = serverRepository.findFirstByAssetInfoSerialNumber(basicInfo.assetInfo.serialNumber);
        if (currentServer != null) {
            throw new ResourceAlreadyExistException();
        }
        var initializedServer = Server.initialize(basicInfo);
        initializedServer = serverRepository.save(initializedServer);
        var context = new AddServerContext();
        return context.run();
    }

    public Server getServerById(String id) {
        return null;
    }

    public Server removeServerById(String id) {
        return null;
    }

    public List<Server> getServerList() {
        return null;
    }

    public Page<Server> getServerCollection(int pageIndex, int pageSize, Direction direction, String orderBy) {
        return serverRepository.findAll(PageRequest.of(pageIndex, pageSize, direction, orderBy));
    }

    /**
     * Get the {@link ProcessorResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link ProcessorResource}
     */
    public ProcessorResource getServerProcessorResourceById(@NotNull String id) {
        return processorRepository.findById(id).get();
    }

    /**
     * Get the {@link MemoryResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link MemoryResource}
     */
    public MemoryResource getServerMemoryResourceById(String id) {
        return memoryRepository.findById(id).get();
    }

    /**
     * Get the {@link BiosResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link BiosResource}
     */
    public BiosResource getServerBiosResourceById(String id) {
        return biosRepository.findById(id).get();
    }

    /**
     * Get the {@link ManagerResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link ManagerResource}
     */
    public ManagerResource getServerManagerResourceById(String id) {
        return managerRepository.findById(id).get();
    }

    /**
     * Get the {@link BoardResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link BoardResource}
     */
    public BoardResource getServerBoardResourceById(String id) {
        return boardRepository.findById(id).get();
    }

    /**
     * Get the {@link PcieResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link PcieResource}
     */
    public PcieResource getServerPcieResourceById(String id) {
        return pcieRepository.findById(id).get();
    }

    /**
     * Get the {@link AdapterResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link AdapterResource}
     */
    public AdapterResource getServerAdapterResourceById(String id) {
        return adapterRepository.findById(id).get();
    }

    /**
     * Get the {@link PowerResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link PowerResource}
     */
    public PowerResource getServerPowerResourceById(String id) {
        return powerRepository.findById(id).get();
    }

    /**
     * Get the {@link ThermalResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link ThermalResource}
     */
    public ThermalResource getServerThermalResourceById(String id) {
        return thermalRepository.findById(id).get();
    }

    /**
     * Get the {@link FirmwareResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link FirmwareResource}
     */
    public FirmwareResource getServerFirmwareResourceById(String id) {
        return firmwareRepository.findById(id).get();
    }

    /**
     * Get the {@link StorageResource} of a server by server's ID.
     *
     * @param id The server's ID.
     * @return The {@link StorageResource}
     */
    public StorageResource getServerStorageResourceById(String id) {
        return storageRepository.findById(id).get();
    }
}
