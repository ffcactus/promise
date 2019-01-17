package com.huawei.skywalker.server.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.skywalker.server.model.Server;
import com.huawei.skywalker.server.repository.ServerRepository;
import com.huawei.skywalker.server.service.ServerService;
import com.promise.apps.server.sdk.dto.InitServerRequestV1;
import com.promise.apps.server.sdk.dto.ServerStateV1;
import com.promise.platform.test.restdocs.SkywalkerDocumentation;

@SpringBootTest
@ExtendWith({
        RestDocumentationExtension.class, SpringExtension.class
})
public class ServerControllerTest
{
    private static final String ServerRootUri = "/api/v1/servers";

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    ServerService serverService;

    @MockBean
    ServerRepository serverRepository;

    ObjectMapper mapper = new ObjectMapper();

    final static List<FieldDescriptor> getServerFieldDescriptor;

    static
    {
        getServerFieldDescriptor = new ArrayList<FieldDescriptor>(
                Arrays.asList(
                        fieldWithPath("id").description("Employee id"),
                        fieldWithPath("uri").description("Server URI within the system."),
                        fieldWithPath("name").description("Server name."),
                        fieldWithPath("serialNumber").description("Server serial number"),
                        fieldWithPath("state").description("Server state"),
                        fieldWithPath("description").description("Server description").optional(),
                        fieldWithPath("type").description("Server type").optional(),
                        fieldWithPath("computerSystem").description("Server computer system").optional(),
                        fieldWithPath("bios").description("Server BIOS configuration").optional(),
                        fieldWithPath("processors").description("Server processors").optional(),
                        fieldWithPath("memory").description("Server memory").optional(),
                        fieldWithPath("ethernetInterfaces").description("Server ethernet interfaces.").optional(),
                        fieldWithPath("networkInterfaces").description("Server network interfaces.").optional(),
                        fieldWithPath("pcieDevices").description("Server PCIe devices.").optional(),
                        fieldWithPath("pcieFunctions").description("Server PCIe functions.").optional(),
                        fieldWithPath("chassis").description("Server chassis.").optional(),
                        fieldWithPath("fans").description("Server fans.").optional(),
                        fieldWithPath("powerSupplies").description("Server power supplies.").optional(),
                        fieldWithPath("networkAdapters").description("Server network adapters.").optional(),
                        fieldWithPath("softwareInventories").description("Server software inventories.").optional()));
    }

    @BeforeAll
    public static void beforeAll()
    {
    }

    @BeforeEach
    public void beforeEach(RestDocumentationContextProvider restDocumentation)
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(
                        documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                                .withResponseDefaults(prettyPrint()))
                .build();
    }

    @Test
    public void initServer()
            throws Exception
    {
        InitServerRequestV1 initServerRequest = new InitServerRequestV1("serial-number", "username", "password");
        Server serverInited = new Server();
        serverInited.id = "1";
        serverInited.uri = ServerRootUri + "/1";
        serverInited.name = "serial-number";
        serverInited.state = ServerStateV1.Initialized;
        serverInited.assetInfo.serialNumber = "serial-number";

        when(serverService.initServer(initServerRequest)).thenReturn(serverInited);

        mockMvc.perform(
                post(ServerRootUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsBytes(initServerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(
                        document(
                                "init-server",
                                requestFields(
                                        fieldWithPath("serialNumber").description("The serial number of the server."),
                                        fieldWithPath("username").description("The username of the server."),
                                        fieldWithPath("password").description("The password of the server."),
                                        fieldWithPath("name").description("The name of the server.").optional(),
                                        fieldWithPath("description").description("The description of the server.").optional()),
                                responseFields(getServerFieldDescriptor)
                                        .and(SkywalkerDocumentation.scopedResourceResponseSnippet)));
    }

    @Test
    public void getServerById()
            throws JsonProcessingException,
            Exception
    {
        Server server1 = new Server();
        server1.id = "1";
        server1.uri = ServerRootUri + "/1";
        server1.name = "server 1";
        server1.description = "server 1 description.";
        server1.state = ServerStateV1.Ready;
        server1.assetInfo.serialNumber = "serial-number";
        when(serverService.getServerById("1")).thenReturn(server1);

        mockMvc.perform(get(ServerRootUri + "/{id}", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(
                        document(
                                "get-server-by-id",
                                responseFields(getServerFieldDescriptor).and(SkywalkerDocumentation.scopedResourceResponseSnippet),
                                pathParameters(parameterWithName("id").description("server ID"))));
    }

    @Test
    public void removeServerById()
            throws JsonProcessingException,
            Exception
    {
        Server server1 = new Server();
        server1.id = "1";
        server1.uri = ServerRootUri + "/1";
        server1.name = "server 1";
        server1.description = "server 1 description.";
        server1.state = ServerStateV1.Locked;
        server1.assetInfo.serialNumber = "serial-number";
        when(serverService.removeServerById("1")).thenReturn(server1);

        mockMvc.perform(delete(ServerRootUri + "/{id}", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(
                        document(
                                "remove-server-by-id",
                                responseFields(getServerFieldDescriptor).and(SkywalkerDocumentation.scopedResourceResponseSnippet),
                                pathParameters(parameterWithName("id").description("server ID"))));
    }

    @Test
    public void getServerCollection()
            throws JsonProcessingException,
            Exception
    {
        var collection = new ArrayList<Server>();
        for (int i = 1; i < 1; i++)
        {
            String prefix = String.format("%05d", i);
            var server = new Server();
            server.id = "" + prefix;
            server.name = "Server " + prefix;
            collection.add(server);
        }
        var page = new PageImpl<Server>(collection, PageRequest.of(1, 1), 3);
        when(serverService.getServerCollection(1, 1, Direction.ASC, "name")).thenReturn(page);
        mockMvc.perform(get(ServerRootUri + "?pageIndex=1&pageSize=1&order=ASC&orderBy=name").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(
                        document(
                                "get-server-collection",
                                SkywalkerDocumentation.getCollectionResponseSnippet));

    }
}
