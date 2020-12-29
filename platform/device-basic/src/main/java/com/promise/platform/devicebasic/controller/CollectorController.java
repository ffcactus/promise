package com.promise.platform.devicebasic.controller;

import com.promise.platform.common.controller.CommonExceptionController;
import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorListItemV1;
import com.promise.platform.devicebasic.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rest/platform/collectors")
public class CollectorController extends CommonExceptionController {

    @Autowired
    private CollectorService collectorService;

    @GetMapping
    public ResponseEntity<ListResponseV1<GetCollectorListItemV1>> getCollectors(
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "1024") String size,
            @RequestParam(required = false, defaultValue = "") String sortDirection,
            @RequestParam(required = false, defaultValue = "") String sortBy
    ) {
        var resp = collectorService.getAll(new ListRequestV1(page, size, sortDirection, sortBy));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
