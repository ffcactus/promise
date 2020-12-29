package com.promise.collector.httpclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClientUtils {

    public static <T> T getObjectFromResponse(CloseableHttpResponse response, Class<T> valueType) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(reader, valueType);

    }

    public static <T> T getObjectFromResponse(CloseableHttpResponse response, TypeReference<T> valueType) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(reader, valueType);
    }
}
