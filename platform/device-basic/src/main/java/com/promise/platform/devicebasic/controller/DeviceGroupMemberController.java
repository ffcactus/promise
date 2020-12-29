package com.promise.platform.devicebasic.controller;

import com.promise.platform.common.controller.CommonExceptionController;
import com.promise.platform.common.dto.*;
import com.promise.platform.devicebasic.service.DeviceGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rest/platform/device-group-member")
public class DeviceGroupMemberController extends CommonExceptionController {

    @Autowired
    private DeviceGroupMemberService deviceGroupMemberService;

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
    public ResponseEntity<ListResponseV1<MappingListItemV1>> getAll(
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "1024") String size,
            @RequestParam(required = false, defaultValue = "") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String sortBy
    ) {
        // TODO support filter.
        var resp = deviceGroupMemberService.getAll(new ListRequestV1(page, size, sortDirection, sortBy));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PatchMapping("/{id}/action/bulkUpdate")
    public ResponseEntity<GroupMemberUpdateResponseV1> bulkUpdate(
            @PathVariable Long id,
            @RequestBody GroupMemberUpdateRequestV1 request
    ) {
        return new ResponseEntity<>(deviceGroupMemberService.bulkUpdate(id, request), HttpStatus.OK);
    }


}
