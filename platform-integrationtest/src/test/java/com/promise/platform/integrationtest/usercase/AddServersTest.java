package com.promise.platform.integrationtest.usercase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorListItemV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.CreateCollectorGroupRequestV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.GetCollectorGroupResponseV1;
import com.promise.platform.integrationtest.common.utility.ClientUtility;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.promise.platform.common.URIs.CollectorBaseUri;
import static com.promise.platform.common.URIs.CollectorGroupBaseUri;

public class AddServersTest {

    private final static String serverAddress = "http://localhost:8080";

    @Test
    void addServers() throws IOException {
        // 1. Remove all devices, devices-groups, collectors and collector groups.
        // 2. Wait for collectors.
        var builder = HttpClientBuilder.create();
        var client = builder.build();
        var mapper = new ObjectMapper();

        final var httpGet = new HttpGet(serverAddress + CollectorBaseUri);
        var httpResponse = client.execute(httpGet);
        var collectors = ClientUtility.getObjectFromResponse(httpResponse, new TypeReference<ListResponseV1<GetCollectorListItemV1>>() {
        });
        // assertEquals(4, collectors.getTotal());

        // 3. Create 2 collector group.
        var collectorGroupRequest1 = new CreateCollectorGroupRequestV1();
        collectorGroupRequest1.setName("group 1");
        var collectorGroupRequest2 = new CreateCollectorGroupRequestV1();
        collectorGroupRequest2.setName("group 2");
        var httpPost = new HttpPost(serverAddress + CollectorGroupBaseUri);
        httpPost.setEntity(new StringEntity(mapper.writeValueAsString(collectorGroupRequest1), ContentType.APPLICATION_JSON));
        httpResponse = client.execute(httpPost);
        var collectorGroup1 = ClientUtility.getObjectFromResponse(httpResponse, GetCollectorGroupResponseV1.class);
        httpPost.setEntity(new StringEntity(mapper.writeValueAsString(collectorGroupRequest2), ContentType.APPLICATION_JSON));
        httpResponse = client.execute(httpPost);
        httpResponse = client.execute(httpPost);
        var collectorGroup2 = ClientUtility.getObjectFromResponse(httpResponse, GetCollectorGroupResponseV1.class);
    }
}
