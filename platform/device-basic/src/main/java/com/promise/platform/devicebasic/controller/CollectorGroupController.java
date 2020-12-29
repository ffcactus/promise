package com.promise.platform.devicebasic.controller;

import com.promise.platform.common.controller.CommonExceptionController;
import com.promise.platform.common.dto.GroupMemberUpdateRequestV1;
import com.promise.platform.common.dto.GroupMemberUpdateResponseV1;
import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorListItemV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.CreateCollectorGroupRequestV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.GetCollectorGroupListItemV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.GetCollectorGroupResponseV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.UpdateCollectorGroupRequestV1;
import com.promise.platform.devicebasic.service.CollectorGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rest/platform/collector-groups")
public class CollectorGroupController extends CommonExceptionController {
    @Autowired
    private CollectorGroupService collectorGroupService;

    @PostMapping()
    public ResponseEntity<GetCollectorGroupResponseV1> create(@RequestBody CreateCollectorGroupRequestV1 request) {
        var resp = collectorGroupService.create(request);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListResponseV1<GetCollectorGroupListItemV1>> getAll(
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "1024") String size,
            @RequestParam(required = false, defaultValue = "") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String sortBy
    ) {
        var resp = collectorGroupService.getAll(new ListRequestV1(page, size, sortDirection, sortBy));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCollectorGroupResponseV1> getById(@PathVariable Long id) {
        return new ResponseEntity<>(collectorGroupService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetCollectorGroupResponseV1> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(collectorGroupService.deleteById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCollectorGroupResponseV1> updateById(
            @PathVariable Long id,
            @RequestBody UpdateCollectorGroupRequestV1 request
    ) {
        return new ResponseEntity<>(collectorGroupService.updateById(id, request), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<ListResponseV1<GetCollectorListItemV1>> getMembers(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "1024") String size,
            @RequestParam(required = false, defaultValue = "") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String sortBy
    ) {
        return new ResponseEntity<>(
                collectorGroupService.getMembers(id, new ListRequestV1(page, size, sortDirection, sortBy)),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}/members")
    public ResponseEntity<GroupMemberUpdateResponseV1> patchMembers(
            @PathVariable Long id,
            @RequestBody GroupMemberUpdateRequestV1 request
    ) {
        return new ResponseEntity<>(collectorGroupService.updateMember(id, request), HttpStatus.OK);
    }
}
