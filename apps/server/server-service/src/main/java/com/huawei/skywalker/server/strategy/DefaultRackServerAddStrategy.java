package com.huawei.skywalker.server.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.huawei.skywalker.server.client.ServerHardwareClient;
import com.huawei.skywalker.server.model.AdapterResource;
import com.huawei.skywalker.server.model.BiosResource;
import com.huawei.skywalker.server.model.BoardResource;
import com.huawei.skywalker.server.model.FirmwareResource;
import com.huawei.skywalker.server.model.ManagerResource;
import com.huawei.skywalker.server.model.MemoryResource;
import com.huawei.skywalker.server.model.PcieResource;
import com.huawei.skywalker.server.model.PowerResource;
import com.huawei.skywalker.server.model.ProcessorResource;
import com.huawei.skywalker.server.model.Server;
import com.huawei.skywalker.server.model.StorageResource;
import com.huawei.skywalker.server.model.ThermalResource;
import com.huawei.skywalker.server.repository.AdapterResourceRepository;
import com.huawei.skywalker.server.repository.BiosResourceRepository;
import com.huawei.skywalker.server.repository.BoardResourceRepository;
import com.huawei.skywalker.server.repository.FirmwareResourceRepository;
import com.huawei.skywalker.server.repository.ManagerResourceRepository;
import com.huawei.skywalker.server.repository.MemoryResourceRepository;
import com.huawei.skywalker.server.repository.PcieResourceRepository;
import com.huawei.skywalker.server.repository.PowerResourceRepository;
import com.huawei.skywalker.server.repository.ProcessorResourceRepository;
import com.huawei.skywalker.server.repository.ServerRepository;
import com.huawei.skywalker.server.repository.StorageResourceRepository;
import com.huawei.skywalker.server.repository.ThermalResourceRepository;
import com.promise.platform.sdk.exception.ResourceNotFoundException;

/**
 * The default rack server add strategy.
 *
 */
@Component
public class DefaultRackServerAddStrategy implements ServerAddStrategy
{
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

    @Override
    public Server refreshServer(String id)
    {
        return serverRepository.findById(id).get();

    }

    @Override
    public Server refreshProcessorResource(String id, ServerHardwareClient client)
    {
        return updateProcessorResource(id, client.getProcessorResource());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateProcessorResource(String id, ProcessorResource resource)
    {
        processorRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.processorSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshMemoryResource(String id, ServerHardwareClient client)
    {
        return updateMemoryResource(id, client.getMemoryResource());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateMemoryResource(String id, MemoryResource resource)
    {
        memoryRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.memorySummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshManagerResource(String id, ServerHardwareClient client)
    {
        return updateManagerResource(id, client.getManagerResource());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateManagerResource(String id, ManagerResource resource)
    {
        managerRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.managerSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshStorageResource(String id, ServerHardwareClient client)
    {
        return updateStorageResource(id, client.getStorageResource());
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateStorageResource(String id, StorageResource resource)
    {
        storageRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.storageSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshBiosResource(String id, ServerHardwareClient client)
    {
        return updateBiosResource(id, client.getBiosResource());
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateBiosResource(String id, BiosResource resource)
    {
        biosRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.biosSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshBoardResource(String id, ServerHardwareClient client)
    {
        return updateBoardResource(id, client.getBoardResource());
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateBoardResource(String id, BoardResource resource)
    {
        boardRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.boardSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshPcieResource(String id, ServerHardwareClient client)
    {
        return updatePcieResource(id, client.getPcieResource());
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updatePcieResource(String id, PcieResource resource)
    {
        pcieRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.pcieSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshAdapterResource(String id, ServerHardwareClient client)
    {
        return updateAdapterResource(id, client.getAdapterResource());
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateAdapterResource(String id, AdapterResource resource)
    {
        adapterRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.adapterSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshPowerResource(String id, ServerHardwareClient client)
    {
        return updatePowerResource(id, client.getPowerResource());
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updatePowerResource(String id, PowerResource resource)
    {
        powerRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.powerSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshThermalResource(String id, ServerHardwareClient client)
    {
        return updateThermalResource(id, client.getThermalResource());
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateThermalResource(String id, ThermalResource resource)
    {
        thermalRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.thermalSummary = resource.summary;
        return serverRepository.save(server);
    }

    @Override
    public Server refreshFirmwareResource(String id, ServerHardwareClient client)
    {
        return updateFirmwareResource(id, client.getFirmwareResource());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    private Server updateFirmwareResource(String id, FirmwareResource resource)
    {
        firmwareRepository.save(resource);
        var queryResult = serverRepository.findById(id);
        if (queryResult.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        var server = queryResult.get();
        server.firmwareSummary = resource.summary;
        return serverRepository.save(server);
    }
}
