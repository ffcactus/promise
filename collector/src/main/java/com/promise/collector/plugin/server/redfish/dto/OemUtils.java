package com.promise.collector.plugin.server.redfish.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class OemUtils {

    private OemUtils() {}

    public static String getOemPropertyAsString(JsonNode node, String vendor, String property) {
        var vendorNode = node.get(vendor);
        return vendorNode.get(property).asText();
    }

    public static int getOemPropertyAsInteger(JsonNode node, String vendor, String property) {
        var vendorNode = node.get(vendor);
        return vendorNode.get(property).asInt();
    }

    public static long getOemPropertyAsLong(JsonNode node, String vendor, String property) {
        var vendorNode = node.get(vendor);
        return vendorNode.get(property).asLong();
    }

}
