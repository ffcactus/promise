package com.promise.platform.devicebasic.controller;

import com.promise.platform.common.controller.CommonExceptionController;
import com.promise.platform.common.dto.ErrorResponseV1;
import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.devicebasic.exception.DeviceAlreadyExistException;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicebasic.sdk.dto.device.GetDeviceListItemV1;
import com.promise.platform.devicebasic.sdk.dto.device.GetDeviceResponseV1;
import com.promise.platform.devicebasic.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.promise.platform.task.sdk.dto.Headers.TASK_URL_HEADER;

/**
 * The controller for device related operation.
 */
@RestController
@RequestMapping("/api/v1/rest/platform/devices")
public class DeviceController extends CommonExceptionController {

    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    /**
     * Get all the devices.
     *
     * @param page          zero-based page index, must not be negative.
     * @param size          the size of the page to be returned, must be greater than 0.
     * @param sortDirection the sort direction.
     * @param sortBy        the sort by.
     * @return The devices match the request.
     */
    @GetMapping
    public ResponseEntity<ListResponseV1<GetDeviceListItemV1>> getAll(
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "1024") String size,
            @RequestParam(required = false, defaultValue = "") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String sortBy
    ) {
        var resp = deviceService.getAll(new ListRequestV1(page, size, sortDirection, sortBy));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * Discover a device.
     * That is by given the access information, check if the device can be managed by this platform. If it can be
     * managed, save it on the platform.
     *
     * @param request The discover request.
     * @return The HTTP response including body, status, headings.
     * @throws Exception When the discovery failed.
     */
    @PostMapping("/actions/discover")
    public ResponseEntity<GetDeviceResponseV1> discover(@RequestBody DiscoverDeviceRequestV1 request) throws Exception {
        var resp = deviceService.discoverDevice(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDeviceResponseV1> get(@PathVariable Long id) {
        var resp = deviceService.get(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * Remove a device from the system.
     *
     * @param id The ID of the device.
     * @return The removed device.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDevice(@PathVariable Long id) {
        var taskResponse = deviceService.removeDevice(id);
        var headers = new HttpHeaders();
        headers.add(TASK_URL_HEADER, taskResponse.getTaskUrl());
        return new ResponseEntity<>(taskResponse.getBody(), headers, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(DeviceAlreadyExistException.class)
    public ResponseEntity<ErrorResponseV1> deviceAlreadyExist(DeviceAlreadyExistException e) {
        var errResp = new ErrorResponseV1(e.getErrorCode(), e.getReason(), e.getArguments());
        return new ResponseEntity<>(errResp, HttpStatus.BAD_REQUEST);
    }

}
