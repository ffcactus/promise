package com.huawei.skywalker.server.client;

import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;

import com.huawei.skywalker.server.client.dto.GetChassisResponse;
import com.huawei.skywalker.server.client.dto.GetComputerSystemResponse;
import com.huawei.skywalker.server.client.dto.GetProcessorResponse;
import com.huawei.skywalker.server.model.AdapterResource;
import com.huawei.skywalker.server.model.BiosResource;
import com.huawei.skywalker.server.model.BoardResource;
import com.huawei.skywalker.server.model.FirmwareResource;
import com.huawei.skywalker.server.model.ManagerResource;
import com.huawei.skywalker.server.model.MemoryResource;
import com.huawei.skywalker.server.model.PcieResource;
import com.huawei.skywalker.server.model.PowerResource;
import com.huawei.skywalker.server.model.ProcessorResource;
import com.huawei.skywalker.server.model.ServerBasicInfo;
import com.huawei.skywalker.server.model.StorageResource;
import com.huawei.skywalker.server.model.ThermalResource;

/**
 * Huawei server hardware client implementation of {@link ServerHardwareClient}.
 *
 */
public class HuaweiServerHardwareClient implements ServerHardwareClient
{
    private RestClient restClient;

    public HuaweiServerHardwareClient(String address, String username, String password)
    {
        this.restClient = new RestClient(address, username, password);
    }

    @Override
    public ServerBasicInfo getServerBasicInfo()
    {
        ResponseEntity<GetChassisResponse> computerSystem = restClient.get("/redfish/v1/Chassis/1", GetChassisResponse.class);
        if (computerSystem == null)
        {
            return null;
        }
        GetChassisResponse body = computerSystem.getBody();
        if (body != null)
        {
            ServerBasicInfo basicInfo = new ServerBasicInfo();
            basicInfo.assetInfo.manufacturer = body.manufacturer;
            basicInfo.assetInfo.model = body.model;
            basicInfo.assetInfo.serialNumber = body.serialNumber;;
            basicInfo.type = body.chassisType;
            return basicInfo;
        }
        return null;
    }
    
    @Override
    public ProcessorResource getProcessorResource() {
        var ret = new ProcessorResource();
        var computerSystem = restClient.get("/redfish/v1/Systems/1", GetComputerSystemResponse.class);
        ret.summary = computerSystem.getBody().processorSummary.convert();
        ret.processors = restClient.getCollection("/redfish/v1/Systems/1/Processors", GetProcessorResponse.class)
                .stream().map(GetProcessorResponse::convert).collect(Collectors.toList());
        return ret;
    }
    
    

//    @Override
//    public ComputerSystem getComputerSystem()
//    {
//        var computerSystem = restClient.get("/redfish/v1/Systems/1", GetComputerSystemResponse.class);
//        if (computerSystem == null)
//        {
//            return null;
//        }
//        GetComputerSystemResponse body = computerSystem.getBody();
//        if (body != null)
//        {
//            return body.convert();
//        }
//        return null;
//    }

//    @Override
//    public BiosResource getBios()
//    {
//        var response = restClient.get("/redfish/v1/Systems/1/Bios", GetBiosResponse.class);
//        if (response == null)
//        {
//            return null;
//        }
//        GetBiosResponse body = response.getBody();
//        if (body != null)
//        {
//            return body.convert();
//        }
//        return null;
//    }


    /**
     * The implementation of getMemroy.
     */
//    @Override
//    public List<MemoryResource> getMemory()
//    {
//        return restClient.getCollection("/redfish/v1/Systems/1/Memory", GetMemoryResponse.class)
//                .stream().map(GetMemoryResponse::convert).collect(Collectors.toList());
//    }

//    @Override
//    public List<EthernetInterface> getEthernetInterfaces()
//    {
//        // TODO Auto-generated method stub
//        return null;
//    }

//    @Override
//    public List<NetworkInterface> getNetworkInterfaces()
//    {
//        // TODO Auto-generated method stub
//        return null;
//    }

//    @Override
//    public List<PcieDevice> getPcieDevices()
//    {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public List<PcieFunction> getPcieFunctions()
//    {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Chassis getChassis()
//    {
//        var response = restClient.get("/redfish/v1/Chassis/1", GetChassisResponse.class);
//        if (response == null)
//        {
//            return null;
//        }
//        return response.getBody().convert();
//    }
//
//    @Override
//    public List<Fan> getFans()
//    {
//        ResponseEntity<GetThermalResponse> response = restClient.get("/redfish/v1/Chassis/1/Thermal", GetThermalResponse.class);
//        if (response == null)
//        {
//            return null;
//        }
//        GetThermalResponse body = response.getBody();
//        if (body != null)
//        {
//            return body.getFans().stream().map(FanResponse::convert).collect(Collectors.toList());
//        }
//        return null;
//    }
//
//    @Override
//    public List<PowerSupply> getPowerSupplies()
//    {
//        ResponseEntity<GetPowerResponse> response = restClient.get("/redfish/v1/Chassis/1/Power", GetPowerResponse.class);
//        if (response == null)
//        {
//            return null;
//        }
//        GetPowerResponse body = response.getBody();
//        if (body != null)
//        {
//            return body.getPowerSupplies().stream().map(PowerSupplyResponse::convert).collect(Collectors.toList());
//        }
//        return null;
//    }
//
//    @Override
//    public List<NetworkAdapter> getNetworkAdapters()
//    {
//        List<NetworkAdapter> retAdapters = new ArrayList<NetworkAdapter>();
//        List<GetNetworkAdapterResponse> getAdapters = restClient
//                .getCollection("/redfish/v1/Chassis/1/NetworkAdapters", GetNetworkAdapterResponse.class);
//
//        for (GetNetworkAdapterResponse getAdapter : getAdapters)
//        {
//            List<NetworkAdapterController> retControllers = new ArrayList<NetworkAdapterController>();
//
//            for (NetworkAdapterControllerResponse getController : getAdapter.controllers)
//            {
//                List<NetworkPort> ports = getController.link.networkPorts.stream()
//                        .map(id -> restClient.get(id.odataid, NetworkPort.class))
//                        .map(response -> response.getBody())
//                        .collect(Collectors.toList());
//                NetworkAdapterController retController = new NetworkAdapterController();
//                retController.networkPorts = ports;
//                retControllers.add(retController);
//
//            }
//            retAdapters.add(getAdapter.convert(retControllers));
//        }
//        return retAdapters;
//    }
//
//    @Override
//    public List<SoftwareInventory> getSoftwareInventories()
//    {
//        return restClient.getCollection("/redfish/v1/UpdateService/FirmwareInventory", GetSoftwareInventoryResponse.class)
//                .stream().map(GetSoftwareInventoryResponse::convert).collect(Collectors.toList());
//    }

    @Override
    public @NotNull MemoryResource getMemoryResource()
    {
        var ret = new MemoryResource();
        return ret;
    }
    
    @Override
    public @NotNull StorageResource getStorageResource()
    {
        var ret = new StorageResource();
        return ret;
    }


    @Override
    public @NotNull BiosResource getBiosResource()
    {
        var ret = new BiosResource();
        return ret;
    }

    @Override
    public @NotNull ManagerResource getManagerResource()
    {
        var ret = new ManagerResource();
        return ret;
    }

    @Override
    public @NotNull BoardResource getBoardResource()
    {
        var ret = new BoardResource();
        return ret;
    }

    @Override
    public @NotNull PcieResource getPcieResource()
    {
        var ret = new PcieResource();
        return ret;
    }

    @Override
    public @NotNull AdapterResource getAdapterResource()
    {
        var ret = new AdapterResource();
        return ret;
    }

    @Override
    public @NotNull PowerResource getPowerResource()
    {
        var ret = new PowerResource();
        return ret;
    }

    @Override
    public @NotNull ThermalResource getThermalResource()
    {
        var ret = new ThermalResource();
        return ret;
    }

    @Override
    public @NotNull FirmwareResource getFirmwareResource()
    {
        var ret = new FirmwareResource();
        return ret;
    }

}
