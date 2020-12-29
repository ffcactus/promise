package com.promise.platform.integrationtest.common.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientUtility {
    public static <T> T getObjectFromResponse(CloseableHttpResponse response, Class<T> valueType) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            var objectMapper = new ObjectMapper();
            return objectMapper.readValue(reader, valueType);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> T getObjectFromResponse(CloseableHttpResponse response, TypeReference<T> valueType) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            var objectMapper = new ObjectMapper();
            return objectMapper.readValue(reader, valueType);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
