package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.dto.MappingListItemV1;
import com.promise.platform.common.entity.MappingEntity;
import com.promise.platform.common.util.ResourceUtils;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorListItemV1;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorResponseV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.CreateCollectorGroupRequestV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.GetCollectorGroupListItemV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.GetCollectorGroupResponseV1;
import com.promise.platform.devicebasic.sdk.dto.device.*;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.CreateDeviceGroupRequestV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.GetDeviceGroupListItemV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.GetDeviceGroupResponseV1;
import com.promise.platform.devicebasic.sdk.ws.DeviceMonitorRequestV1;

import java.util.stream.Collectors;

public class EntityConverter {

    public static DeviceGroupEntity generate(CreateDeviceGroupRequestV1 dto) {
        var entity = new DeviceGroupEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static CollectorGroupEntity generate(CreateCollectorGroupRequestV1 dto) {
        var entity = new CollectorGroupEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    /**
     * Convert {@link DiscoverDeviceRequestV1} to {@link DiscoverDeviceRequestEntity}
     *
     * @param request {@link DiscoverDeviceRequestV1}
     * @return {@link DiscoverDeviceRequestEntity}
     */
    public static DiscoverDeviceRequestEntity toDiscoverRequestEntity(DiscoverDeviceRequestV1 request) {
        var entity = new DiscoverDeviceRequestEntity();
        entity.setAddress(request.getAddress());
        entity.setUsername(request.getUsername());
        entity.setPassword(request.getPassword());
        return entity;
    }

    public static DeviceEntity generate(GetDeviceResponseV1 dto, DiscoverDeviceRequestV1 request) {
        var entity = new DeviceEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setVendor(dto.getVendor());
        entity.setType(dto.getType());
        entity.setModel(dto.getModel());
        entity.setSerialNumber(dto.getSn());
        entity.setSubModel(dto.getSubModel());
        entity.setDht(dto.getDht());
        var processors = dto.getProcessors();
        if (processors != null) {
            entity.setProcessors(processors.stream().map(processor -> {
                var processorEntity = new ProcessorEntity();
                processorEntity.setDevice(entity);
                processorEntity.setLocation(processor.getLocation());
                processorEntity.setModel(processor.getLocation());
                processorEntity.setManufacturer(processor.getManufacturer());
                processorEntity.setSerialNumber(processor.getSerialNumber());
                processorEntity.setPartNumber(processor.getPartNumber());
                return processorEntity;
            }).collect(Collectors.toList()));
        }
        var memory = dto.getMemory();
        if (memory != null) {
            entity.setMemory(memory.stream().map(m -> {
                var memoryEntity = new MemoryEntity();
                memoryEntity.setDevice(entity);
                memoryEntity.setLocation(m.getLocation());
                memoryEntity.setModel(m.getLocation());
                memoryEntity.setManufacturer(m.getManufacturer());
                memoryEntity.setSerialNumber(m.getSerialNumber());
                memoryEntity.setPartNumber(m.getPartNumber());
                memoryEntity.setCapacity(m.getCapacity());
                return memoryEntity;
            }).collect(Collectors.toList()));
        }
        var drives = dto.getDrives();
        if (drives != null) {
            entity.setDrives(drives.stream().map(drive -> {
                var driveEntity = new DriveEntity();
                driveEntity.setDevice(entity);
                driveEntity.setLocation(drive.getLocation());
                driveEntity.setModel(drive.getLocation());
                driveEntity.setManufacturer(drive.getManufacturer());
                driveEntity.setSerialNumber(drive.getSerialNumber());
                driveEntity.setPartNumber(drive.getPartNumber());
                driveEntity.setCapacity(drive.getCapacity());
                return driveEntity;
            }).collect(Collectors.toList()));
        }
        var fans = dto.getFans();
        if (fans != null) {
            entity.setFans(fans.stream().map(fan -> {
                var fanEntity = new FanEntity();
                fanEntity.setDevice(entity);
                fanEntity.setLocation(fan.getLocation());
                fanEntity.setModel(fan.getLocation());
                fanEntity.setManufacturer(fan.getManufacturer());
                fanEntity.setSerialNumber(fan.getSerialNumber());
                fanEntity.setPartNumber(fan.getPartNumber());
                return fanEntity;
            }).collect(Collectors.toList()));
        }
        var powerSupplies = dto.getPowerSupplies();
        if (powerSupplies != null) {
            entity.setPowerSupplies(dto.getPowerSupplies().stream().map(powerSupply -> {
                var powerSupplyEntity = new PowerSupplyEntity();
                powerSupplyEntity.setDevice(entity);
                powerSupplyEntity.setLocation(powerSupply.getLocation());
                powerSupplyEntity.setModel(powerSupply.getLocation());
                powerSupplyEntity.setManufacturer(powerSupply.getManufacturer());
                powerSupplyEntity.setSerialNumber(powerSupply.getSerialNumber());
                powerSupplyEntity.setPartNumber(powerSupply.getPartNumber());
                return powerSupplyEntity;
            }).collect(Collectors.toList()));
        }
        var cards = dto.getCards();
        if (cards != null) {
            entity.setCards(dto.getCards().stream().map(card -> {
                var cardEntity = new CardEntity();
                cardEntity.setDevice(entity);
                cardEntity.setLocation(card.getLocation());
                cardEntity.setModel(card.getLocation());
                cardEntity.setManufacturer(card.getManufacturer());
                cardEntity.setSerialNumber(card.getSerialNumber());
                cardEntity.setPartNumber(card.getPartNumber());
                return cardEntity;
            }).collect(Collectors.toList()));
        }
        var requestEntity = toDiscoverRequestEntity(request);
        requestEntity.setDevice(entity);
        entity.setDiscoverRequest(requestEntity);
        return entity;
    }

    /**
     * Convert {@link CreateDeviceGroupRequestV1} to {@link DeviceGroupEntity}
     *
     * @param request {@link CreateDeviceGroupRequestV1}
     * @return {@link DeviceGroupEntity}
     */
    public static DeviceGroupEntity toDeviceGroupEntity(CreateDeviceGroupRequestV1 request) {
        var entity = new DeviceGroupEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setType(request.getType());
        return entity;
    }

    /**
     * Convert {@link MappingEntity} to {@link MappingListItemV1}
     *
     * @param entity {@link MappingEntity}
     * @return {@link MappingListItemV1}
     */
    public static MappingListItemV1 toMappingListItem(MappingEntity entity) {
        return new MappingListItemV1(entity.getId(), entity.getLeftId(), entity.getRightId());
    }

    /**
     * Convert {@link DeviceEntity} to {@link GetDeviceResponseV1}
     *
     * @param entity {@link DeviceEntity}
     * @return {@link GetDeviceResponseV1}
     */
    public static GetDeviceResponseV1 toDiscoverDeviceResponse(DeviceEntity entity) {
        var dto = new GetDeviceResponseV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        dto.setType(entity.getType());
        dto.setVendor(entity.getVendor());
        dto.setModel(entity.getModel());
        dto.setSubModel(entity.getSubModel());
        dto.setSn(entity.getSerialNumber());
        dto.setDht(entity.getDht());
        var processors = entity.getProcessors();
        if (processors != null) {
            dto.setProcessors(entity.getProcessors().stream().map(e -> {
                var d = new ProcessorV1();
                d.setLocation(e.getLocation());
                d.setModel(e.getModel());
                d.setManufacturer(e.getManufacturer());
                d.setSerialNumber(e.getSerialNumber());
                d.setPartNumber(e.getPartNumber());
                return d;
            }).collect(Collectors.toList()));
        }
        var memory = entity.getMemory();
        if (memory != null) {
            dto.setMemory(memory.stream().map(e -> {
                var d = new MemoryV1();
                d.setLocation(e.getLocation());
                d.setModel(e.getModel());
                d.setManufacturer(e.getManufacturer());
                d.setSerialNumber(e.getSerialNumber());
                d.setPartNumber(e.getPartNumber());
                d.setCapacity(e.getCapacity());
                return d;
            }).collect(Collectors.toList()));
        }
        var drives = entity.getDrives();
        if (drives != null) {
            dto.setDrives(drives.stream().map(e -> {
                var d = new DriveV1();
                d.setLocation(e.getLocation());
                d.setModel(e.getModel());
                d.setManufacturer(e.getManufacturer());
                d.setSerialNumber(e.getSerialNumber());
                d.setPartNumber(e.getPartNumber());
                d.setCapacity(e.getCapacity());
                return d;
            }).collect(Collectors.toList()));
        }
        var fans = entity.getFans();
        if (fans != null) {
            dto.setFans(fans.stream().map(e -> {
                var d = new FanV1();
                d.setLocation(e.getLocation());
                d.setModel(e.getModel());
                d.setManufacturer(e.getManufacturer());
                d.setSerialNumber(e.getSerialNumber());
                d.setPartNumber(e.getPartNumber());
                return d;
            }).collect(Collectors.toList()));
        }
        var powerSupplies = entity.getPowerSupplies();
        if (powerSupplies != null) {
            dto.setPowerSupplies(powerSupplies.stream().map(e -> {
                var d = new PowerSupplyV1();
                d.setLocation(e.getLocation());
                d.setModel(e.getModel());
                d.setManufacturer(e.getManufacturer());
                d.setSerialNumber(e.getSerialNumber());
                d.setPartNumber(e.getPartNumber());
                return d;
            }).collect(Collectors.toList()));
        }
        var cards = entity.getCards();
        if (cards != null) {
            dto.setCards(cards.stream().map(e -> {
                var d = new CardV1();
                d.setLocation(e.getLocation());
                d.setModel(e.getModel());
                d.setManufacturer(e.getManufacturer());
                d.setSerialNumber(e.getSerialNumber());
                d.setPartNumber(e.getPartNumber());
                return d;
            }).collect(Collectors.toList()));
        }

        return dto;
    }

    /**
     * Convert {@link DeviceEntity} to {@link GetDeviceListItemV1}
     *
     * @param entity {@link DeviceEntity}
     * @return {@link GetDeviceListItemV1}
     */
    public static GetDeviceListItemV1 toDeviceListItem(DeviceEntity entity) {
        var dto = new GetDeviceListItemV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        dto.setType(entity.getType());
        dto.setVendor(entity.getVendor());
        dto.setModel(entity.getModel());
        dto.setSubModel(entity.getSubModel());
        dto.setSn(entity.getSerialNumber());
        dto.setDht(entity.getDht());
        return dto;
    }

    /**
     * Convert {@link DeviceEntity} to {@link DeviceMonitorRequestV1}
     *
     * @param entity {@link DeviceEntity}
     * @return {@link DeviceMonitorRequestV1}
     */
    public static DeviceMonitorRequestV1 toDeviceMonitorRequest(DeviceEntity entity) {
        var request = new DeviceMonitorRequestV1();
        request.setType(entity.getType());
        request.setVendor(entity.getVendor());
        request.setModel(entity.getModel());
        request.setSubModel(entity.getSubModel());
        request.setSn(entity.getSerialNumber());
        request.setDht(entity.getDht());
        var discoverRequest = new DiscoverDeviceRequestV1();
        var discoverRequestEntity = entity.getDiscoverRequest();
        discoverRequest.setAddress(discoverRequestEntity.getAddress());
        discoverRequest.setUsername(discoverRequestEntity.getUsername());
        discoverRequest.setPassword(discoverRequestEntity.getPassword());
        request.setDiscoverRequest(discoverRequest);
        return request;
    }

    /**
     * Convert {@link CollectorEntity} to {@link GetCollectorResponseV1}
     *
     * @param entity {@link CollectorEntity}
     * @return {@link GetCollectorResponseV1}
     */
    public static GetCollectorResponseV1 toCollectorResponse(CollectorEntity entity) {
        var dto = new GetCollectorResponseV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        dto.setIp(entity.getIp());
        dto.setSn(entity.getSn());
        dto.setVersion(entity.getVersion());
        dto.setConnected(entity.isConnected());
        dto.setLastConnectedAt(entity.getLastConnectedAt());
        dto.setLastLostAt(entity.getLastLostAt());
        return dto;
    }

    /**
     * Convert {@link CollectorEntity} to {@link GetCollectorListItemV1}
     *
     * @param entity {@link CollectorEntity}
     * @return {@link GetCollectorListItemV1}
     */
    public static GetCollectorListItemV1 toCollectorListItem(CollectorEntity entity) {
        var dto = new GetCollectorListItemV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        dto.setIp(entity.getIp());
        dto.setSn(entity.getSn());
        dto.setVersion(entity.getVersion());
        dto.setConnected(entity.isConnected());
        dto.setLastConnectedAt(entity.getLastConnectedAt());
        dto.setLastLostAt(entity.getLastLostAt());
        return dto;
    }

    /**
     * Convert {@link CollectorGroupEntity} to {@link GetCollectorGroupResponseV1}
     *
     * @param entity {@link CollectorGroupEntity}
     * @return {@link GetCollectorGroupResponseV1}
     */
    public static GetCollectorGroupResponseV1 toCollectorGroupResponse(CollectorGroupEntity entity) {
        var dto = new GetCollectorGroupResponseV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        return dto;
    }

    /**
     * Convert {@link CollectorGroupEntity} to {@link GetCollectorGroupListItemV1}
     *
     * @param entity {@link CollectorGroupEntity}
     * @return {@link GetCollectorGroupListItemV1}
     */
    public static GetCollectorGroupListItemV1 toCollectorGroupListItem(CollectorGroupEntity entity) {
        var dto = new GetCollectorGroupListItemV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        return dto;
    }

    /**
     * Convert {@link DeviceGroupEntity} to {@link GetDeviceGroupResponseV1}
     *
     * @param entity {@link DeviceGroupEntity}
     * @return {@link GetDeviceGroupResponseV1}
     */
    public static GetDeviceGroupResponseV1 toDeviceGroupResponse(DeviceGroupEntity entity) {
        var dto = new GetDeviceGroupResponseV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        dto.setType(entity.getType());
        return dto;
    }

    /**
     * Convert {@link DeviceGroupEntity} to {@link GetDeviceGroupListItemV1}
     *
     * @param entity {@link DeviceGroupEntity}
     * @return {@link GetDeviceGroupListItemV1}
     */
    public static GetDeviceGroupListItemV1 toDeviceGroupListItem(DeviceGroupEntity entity) {
        var dto = new GetDeviceGroupListItemV1();
        ResourceUtils.fromEntityToResource(dto, entity);
        dto.setType(entity.getType());
        return dto;
    }

}
