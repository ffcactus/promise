package com.promise.platform.devicemonitor.controller;

import com.promise.platform.devicemonitor.sdk.dto.GetDeviceStateRecordResponseV1;
import com.promise.platform.devicemonitor.service.DeviceStateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rest/device-monitor/")
public class MonitorController {

    @Autowired
    private DeviceStateRecordService deviceStateRecordService;

    @GetMapping("/{id}")
    public ResponseEntity<GetDeviceStateRecordResponseV1> getDeviceMonitorRecord(@PathVariable Long id) {
        return new ResponseEntity<>(
                deviceStateRecordService.getDeviceStateRecord(id),
                HttpStatus.OK
        );
    }
}
