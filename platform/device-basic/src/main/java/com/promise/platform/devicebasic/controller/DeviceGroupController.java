package com.promise.platform.devicebasic.controller;

import com.promise.platform.common.controller.CommonExceptionController;
import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.CreateDeviceGroupRequestV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.GetDeviceGroupListItemV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.GetDeviceGroupResponseV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.UpdateDeviceGroupRequestV1;
import com.promise.platform.devicebasic.service.DeviceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rest/platform/device-groups")
public class DeviceGroupController extends CommonExceptionController {

    @Autowired
    private DeviceGroupService deviceGroupService;

    @PostMapping()
    public ResponseEntity<GetDeviceGroupResponseV1> create(@RequestBody CreateDeviceGroupRequestV1 request) {
        var resp = deviceGroupService.create(request);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListResponseV1<GetDeviceGroupListItemV1>> getAll(
            @RequestParam(required = false, defaultValue = "0") String from,
            @RequestParam(required = false, defaultValue = "1024") String to,
            @RequestParam(required = false, defaultValue = "") String order,
            @RequestParam(required = false, defaultValue = "") String orderBy
    ) {
        var resp = deviceGroupService.getAll(new ListRequestV1(from, to, order, orderBy));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDeviceGroupResponseV1> getById(@PathVariable Long id) {
        var resp = deviceGroupService.getById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetDeviceGroupResponseV1> deleteById(@PathVariable Long id) {
        var resp = deviceGroupService.deleteById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetDeviceGroupResponseV1> updateById(@PathVariable Long id, @RequestBody UpdateDeviceGroupRequestV1 request) {
        var resp = deviceGroupService.updateById(id, request);
        return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);
    }
}
