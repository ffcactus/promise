package com.promise.collector.plugin.server.redfish.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RedfishChassisTest {
    public static final String example = "{\n" +
            "    \"@odata.context\": \"/redfish/v1/$metadata#Chassis/Members/$entity\",\n" +
            "    \"@odata.id\": \"/redfish/v1/Chassis/1\",\n" +
            "    \"@odata.type\": \"#Chassis.v1_4_0.Chassis\",\n" +
            "    \"Id\": \"1\",\n" +
            "    \"Name\": \"Computer System Chassis\",\n" +
            "    \"ChassisType\": \"Rack\",\n" +
            "    \"Manufacturer\": \"Huawei\",\n" +
            "    \"Model\": \"2288H V5\",\n" +
            "    \"SerialNumber\": \"2102312BRA10K9001060\",\n" +
            "    \"PartNumber\": \"02312BRA\",\n" +
            "    \"AssetTag\": null,\n" +
            "    \"IndicatorLED\": \"Off\",\n" +
            "    \"Status\": {\n" +
            "        \"State\": \"Enabled\",\n" +
            "        \"Health\": \"OK\"\n" +
            "    },\n" +
            "    \"Oem\": {\n" +
            "        \"Huawei\": {\n" +
            "            \"ChassisID\": null,\n" +
            "            \"InletTemperatureCelsius\": 24,\n" +
            "            \"DriveSummary\": {\n" +
            "                \"Count\": 4,\n" +
            "                \"Status\": {\n" +
            "                    \"HealthRollup\": \"OK\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"PowerSupplySummary\": {\n" +
            "                \"Count\": 2,\n" +
            "                \"Status\": {\n" +
            "                    \"HealthRollup\": \"Critical\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"PackageCode\": null,\n" +
            "            \"Boards\": {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/Boards\"\n" +
            "            },\n" +
            "            \"ThresholdSensors\": {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/ThresholdSensors\"\n" +
            "            },\n" +
            "            \"DiscreteSensors\": {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/DiscreteSensors\"\n" +
            "            },\n" +
            "            \"NetworkAdaptersSummary\": {\n" +
            "                \"Count\": 3,\n" +
            "                \"Status\": {\n" +
            "                    \"HealthRollup\": \"OK\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"DeviceMaxNum\": {\n" +
            "                \"MemoryNum\": 24,\n" +
            "                \"PCIeNum\": 3,\n" +
            "                \"CPUNum\": 2,\n" +
            "                \"DiskNum\": 8,\n" +
            "                \"PowerSupplyNum\": 2,\n" +
            "                \"FanNum\": 4,\n" +
            "                \"MezzCardNum\": 0,\n" +
            "                \"SDCardNum\": 0,\n" +
            "                \"SDContollerNum\": 0,\n" +
            "                \"SecurityModuleNum\": 1\n" +
            "            },\n" +
            "            \"EnergyEfficiency\": {\n" +
            "                \"EnergySavingsPercent\": 5,\n" +
            "                \"PowerSavingsKwh\": 107.5,\n" +
            "                \"CarbonFootprintReductionKg\": 107.177\n" +
            "            },\n" +
            "            \"Actions\": {\n" +
            "                \"#Chassis.ControlIndicatorLED\": {\n" +
            "                    \"target\": \"/redfish/v1/Chassis/1/Oem/Huawei/Actions/Chassis.ControlIndicatorLED\",\n" +
            "                    \"@Redfish.ActionInfo\": \"/redfish/v1/Chassis/1/ControlIndicatorLEDActionInfo\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            "    \"Thermal\": {\n" +
            "        \"@odata.id\": \"/redfish/v1/Chassis/1/Thermal\"\n" +
            "    },\n" +
            "    \"Power\": {\n" +
            "        \"@odata.id\": \"/redfish/v1/Chassis/1/Power\"\n" +
            "    },\n" +
            "    \"NetworkAdapters\": {\n" +
            "        \"@odata.id\": \"/redfish/v1/Chassis/1/NetworkAdapters\"\n" +
            "    },\n" +
            "    \"Links\": {\n" +
            "        \"ComputerSystems\": [\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Systems/1\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"ManagedBy\": [\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Managers/1\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"PCIeDevices\": [\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/PCIeDevices/PCIeCard2\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/PCIeDevices/PCIeCard3\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"Drives\": [\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/Drives/HDDPlaneDisk0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/Drives/HDDPlaneDisk1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/Drives/HDDPlaneDisk2\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"@odata.id\": \"/redfish/v1/Chassis/1/Drives/HDDPlaneDisk3\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
    @Test
    public void testMapping() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        // objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        var chassis = objectMapper.readValue(example, RedfishChassis.class);
        Assert.assertEquals("1", chassis.getId());
        Assert.assertEquals("2288H V5", chassis.getModel());
        Assert.assertEquals("2102312BRA10K9001060", chassis.getSerialNumber());
        Assert.assertEquals("Rack", chassis.getChassisType());
        Assert.assertEquals("02312BRA", chassis.getPartNumber());
        Assert.assertNull(chassis.getAssetTag());
        Assert.assertEquals("Off", chassis.getIndicatorLED());
    }
}
