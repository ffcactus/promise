package com.promise.apps.server.controller;

import com.promise.apps.server.model.*;
import com.promise.apps.server.sdk.dto.*;
import com.promise.apps.server.service.ServerService;
import com.promise.platform.common.controller.CommonExceptionController;
import com.promise.platform.common.dto.UpdateErrorMessageRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/servers")
public class ServerController extends CommonExceptionController {

    @Autowired
    private ServerService service;

    /**
     * Initialize a server that is going to be added later.
     * When the server connect to the system, the system will fetch the server
     * information based on the properties posted in this request.
     *
     * @param initServerRequest The request DTO.
     * @return The server that has been initialized.
     */
    @PostMapping(consumes = {
            "application/json"
    }, produces = {
            "application/json"
    })
    public ResponseEntity<GetServerResponseV1> initServer(@RequestBody InitServerRequestV1 initServerRequest) {
        Server server = service.initServer(initServerRequest);
        if (server != null) {
            return new ResponseEntity<GetServerResponseV1>(Server.toResponse(server), HttpStatus.OK);
        }
        return null;
    }

    /**
     * Add a server based on connection information and server's credential.
     *
     * @param addServerRequest
     * @return
     */
    @PostMapping(path = "/actions/add-server", consumes = {
            "application/json"
    }, produces = {
            "application/json"
    })
    public ResponseEntity<GetServerResponseV1> addServer(@RequestBody AddServerRequestV1 addServerRequest) {
        var asychResult = service.addServer(addServerRequest);
        var headers = new HttpHeaders();
        headers.add("task-uri", asychResult.taskUri);
        return new ResponseEntity<GetServerResponseV1>(Server.toResponse(asychResult.result), headers, HttpStatus.OK);
    }

    /**
     * Get a server by it's ID.
     *
     * @param id The ID of the server.
     * @return The server if exists.
     */
    @GetMapping(path = "/{id}", produces = {
            "application/json"
    })
    public ResponseEntity<GetServerResponseV1> getServerById(@PathVariable String id) {
        Server server = service.getServerById(id);
        if (server != null) {
            return new ResponseEntity<GetServerResponseV1>(Server.toResponse(server), HttpStatus.OK);
        }
        return null;
    }

    /**
     * Get processor resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link ProcessorResourceV1}.
     */
    @GetMapping(path = "/{id}/processor-resource", produces = {
            "application/json"
    })
    public ResponseEntity<ProcessorResourceV1> getServerProcessorResourceById(@PathVariable String id) {
        ProcessorResource resource = service.getServerProcessorResourceById(id);
        return new ResponseEntity<ProcessorResourceV1>(ProcessorResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get memory resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link MemoryResourceV1}.
     */
    @GetMapping(path = "/{id}/memory-resource", produces = {
            "application/json"
    })
    public ResponseEntity<MemoryResourceV1> getServerMemoryResourceById(@PathVariable String id) {
        MemoryResource resource = service.getServerMemoryResourceById(id);
        return new ResponseEntity<MemoryResourceV1>(MemoryResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get storage resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link StorageResourceV1}.
     */
    @GetMapping(path = "/{id}/storage-resource", produces = {
            "application/json"
    })
    public ResponseEntity<StorageResourceV1> getServerStorageResourceById(@PathVariable String id) {
        StorageResource resource = service.getServerStorageResourceById(id);
        return new ResponseEntity<StorageResourceV1>(StorageResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get BIOS resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link BiosResourceV1}.
     */
    @GetMapping(path = "/{id}/bios-resource", produces = {
            "application/json"
    })
    public ResponseEntity<BiosResourceV1> getServerBiosResourceById(@PathVariable String id) {
        BiosResource resource = service.getServerBiosResourceById(id);
        return new ResponseEntity<BiosResourceV1>(BiosResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get manager resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link ManagerResourceV1}.
     */
    @GetMapping(path = "/{id}/manager-resource", produces = {
            "application/json"
    })
    public ResponseEntity<ManagerResourceV1> getServerManagerResourceById(@PathVariable String id) {
        ManagerResource resource = service.getServerManagerResourceById(id);
        return new ResponseEntity<ManagerResourceV1>(ManagerResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get PCIe resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link PcieResourceV1}.
     */
    @GetMapping(path = "/{id}/pcie-resource", produces = {
            "application/json"
    })
    public ResponseEntity<PcieResourceV1> getServerPcieResourceById(@PathVariable String id) {
        PcieResource resource = service.getServerPcieResourceById(id);
        return new ResponseEntity<PcieResourceV1>(PcieResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get adapter resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link PcieResourceV1}.
     */
    @GetMapping(path = "/{id}/adapter-resource", produces = {
            "application/json"
    })
    public ResponseEntity<AdapterResourceV1> getServerAdapterResourceById(@PathVariable String id) {
        AdapterResource resource = service.getServerAdapterResourceById(id);
        return new ResponseEntity<AdapterResourceV1>(AdapterResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get power resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link PowerResourceV1}.
     */
    @GetMapping(path = "/{id}/power-resource", produces = {
            "application/json"
    })
    public ResponseEntity<PowerResourceV1> getServerPowerResourceById(@PathVariable String id) {
        PowerResource resource = service.getServerPowerResourceById(id);
        return new ResponseEntity<PowerResourceV1>(PowerResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get thermal resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link ThermalResourceV1}.
     */
    @GetMapping(path = "/{id}/thermal-resource", produces = {
            "application/json"
    })
    public ResponseEntity<ThermalResourceV1> getServerThermalResourceById(@PathVariable String id) {
        ThermalResource resource = service.getServerThermalResourceById(id);
        return new ResponseEntity<ThermalResourceV1>(ThermalResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Get firmware resource of a server.
     *
     * @param id The ID of the server.
     * @return The HTTP response includes the {@link FirmwareResourceV1}.
     */
    @GetMapping(path = "/{id}/firmware-resource", produces = {
            "application/json"
    })
    public ResponseEntity<FirmwareResourceV1> getServerFirmwareResourceById(@PathVariable String id) {
        FirmwareResource resource = service.getServerFirmwareResourceById(id);
        return new ResponseEntity<FirmwareResourceV1>(FirmwareResource.toResponse(resource), HttpStatus.OK);
    }

    /**
     * Remove a server by it's ID.
     *
     * @param id The ID of the server.
     * @return The removed server if exists.
     */
    @DeleteMapping(path = "/{id}", produces = {
            "application/json"
    })
    public ResponseEntity<GetServerResponseV1> removeServerById(@PathVariable String id) {
        Server server = service.removeServerById(id);
        if (server != null) {
            return new ResponseEntity<GetServerResponseV1>(Server.toResponse(server), HttpStatus.OK);
        }
        return null;
    }

    @GetMapping(produces = {
            "application/json"
    })
    public ResponseEntity<UpdateErrorMessageRequestV1.CollectionResponseV1<GetServerResponseV1>> getServerCollection(
            @RequestParam(name = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Direction order,
            @RequestParam(name = "orderBy", required = false, defaultValue = "name") String orderBy) {
        var page = service.getServerCollection(pageIndex, pageSize, order, orderBy);
        if (page != null) {
            var body = new UpdateErrorMessageRequestV1.CollectionResponseV1<GetServerResponseV1>();
            body.pageIndex = pageIndex;
            body.pageSize = pageSize;
            body.order = order;
            body.orderBy = orderBy;
            body.total = page.getTotalElements();
            body.hasNext = page.hasNext();
            body.hasPrevious = page.hasPrevious();
            var baseUri = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI();
            body.nextPageUri = !body.hasNext ? null : String
                    .format("%s?pageIndex=%d&pageSize=%d&order=%S&orderBy=%s", baseUri, pageIndex + 1, pageSize, order, orderBy);
            body.previousPageUri = !body.hasPrevious ? null : String
                    .format("%s?pageIndex=%d&pageSize=%d&order=%S&orderBy=%s", baseUri, pageIndex - 1, pageSize, order, orderBy);
            var members = page.getContent().stream().map(Server::toResponse).collect(Collectors.toList());
            body.members = members;
            return new ResponseEntity<UpdateErrorMessageRequestV1.CollectionResponseV1<GetServerResponseV1>>(body, HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<GetServerResponseV1> setPower(
            @PathVariable String id,
            @RequestBody ApplyServerProfileRequest request) {
        return null;
    }

    /**
     * Apply the server profile to the server.
     *
     * @param id      Server ID.
     * @param request The request DTO which should include the server profile
     *                URI.
     * @return
     */
    @PostMapping(value = "/{id}", produces = {
            "application/json"
    }, consumes = {
            "application/json"
    })
    public ResponseEntity<GetServerResponseV1> applyServerProfile(
            @PathVariable String id,
            @RequestBody ApplyServerProfileRequest request) {
        return null;
    }
}
