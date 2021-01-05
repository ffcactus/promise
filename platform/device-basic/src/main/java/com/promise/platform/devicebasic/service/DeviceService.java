package com.promise.platform.devicebasic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.common.dto.ErrorResponseV1;
import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.common.util.PagingUtils;
import com.promise.platform.devicebasic.entity.DeviceEntity;
import com.promise.platform.devicebasic.entity.DeviceGroupMemberEntity;
import com.promise.platform.devicebasic.entity.EntityConverter;
import com.promise.platform.devicebasic.exception.DeviceAlreadyExistException;
import com.promise.platform.devicebasic.repository.DeviceGroupMemberRepository;
import com.promise.platform.devicebasic.repository.DeviceRepository;
import com.promise.platform.devicebasic.repository.DgCgMappingRepository;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicebasic.sdk.dto.device.GetDeviceListItemV1;
import com.promise.platform.devicebasic.sdk.dto.device.GetDeviceResponseV1;
import com.promise.platform.devicebasic.sdk.message.Exchanger;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import com.promise.platform.devicebasic.sdk.message.MessageCommand;
import com.promise.platform.task.sdk.dto.TaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

/**
 * The service component for device.
 */
@Service
public class DeviceService {

    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private Exchanger exchanger;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DgCgMappingRepository dgCgMappingRepository;

    @Autowired
    private DeviceGroupMemberRepository deviceGroupMemberRepository;

    public GetDeviceResponseV1 discoverDevice(DiscoverDeviceRequestV1 request)
            throws Exception {
        var msg = new GenericMessage<DiscoverDeviceRequestV1>();
        msg.setCommand(MessageCommand.DeviceDiscover);
        msg.setPayload(request);
        var exchangeResp = exchanger.exchange(msg);
        if (exchangeResp.getCommand() == MessageCommand.Failure) {
            var errResp = objectMapper.convertValue(exchangeResp.getPayload(), ErrorResponseV1.class);
            log.warn("Discover failure, {}", errResp.getMessage());
            throw new Exception();
        }
        // If the discovery success, save the device
        var resp = objectMapper.convertValue(exchangeResp.getPayload(), GetDeviceResponseV1.class);
        var entity = save(EntityConverter.generate(resp, request), request.getCollectorGroupId());
        return EntityConverter.toDiscoverDeviceResponse(entity);
    }

    public TaskResponse<Void> removeDevice(Long id) {
        return null;
    }

    public ListResponseV1<GetDeviceListItemV1> getAll(ListRequestV1 request) {
        return PagingUtils.from(
                deviceRepository.findAll(PagingUtils.from(request)),
                EntityConverter::toDeviceListItem
        );
    }

    public GetDeviceResponseV1 get(Long id) {
        return EntityConverter.toDiscoverDeviceResponse(
                deviceRepository.findById(id).orElseThrow(NoSuchElementException::new)
        );
    }

    /**
     * Save the device.
     * <p/>
     * This operation need duplication checking. Device should also by put into the device group. These operations should
     * be wrapped in a serialized transaction.
     *
     * @param device the device to save.
     * @return the saved device.
     * @throws DeviceAlreadyExistException in case the device already exist.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public DeviceEntity save(DeviceEntity device, Long groupId) throws DeviceAlreadyExistException {
        var opt = deviceRepository.findBySerialNumber(device.getSerialNumber());
        if (opt.isPresent()) {
            log.warn("Device already exist, id = {}, sn = {}", opt.get().getId(), opt.get().getSerialNumber());
            throw new DeviceAlreadyExistException(opt.get().getId());
        }
        device = deviceRepository.save(device);
        // and put it to the device group that mapping to the collector group.
        var dgcgMapping = dgCgMappingRepository.findByRightId(groupId);
        // TODO handle the case that the mapping doesn't exist.
        deviceGroupMemberRepository.save(new DeviceGroupMemberEntity(dgcgMapping.getLeftId(), device.getId()));
        return device;
    }
}
