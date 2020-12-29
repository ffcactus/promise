package com.promise.collector.executor;

import com.promise.collector.plugin.Plugin;
import com.promise.collector.plugin.PluginManager;
import com.promise.platform.common.dto.ErrorResponseV1;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.Callable;

public class DeviceDiscoverAction implements Callable<String> {
    private final static Logger log = LoggerFactory.getLogger(DeviceDiscoverAction.class);
    private final Message request;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WebSocketSession session;
    private final PluginManager pluginManager;

    public DeviceDiscoverAction(WebSocketSession session, Message request, PluginManager pluginManager) {
        this.session = session;
        this.request = request;
        this.pluginManager = pluginManager;
    }

    @Override
    public String call() throws Exception {
        log.info("request id = {}", request.getId());

        // find the most suitable plugin.
        var discoverRequest = objectMapper.convertValue(request.getPayload(), DiscoverDeviceRequestV1.class);
        var plugins = pluginManager.getPlugin(discoverRequest);
        Plugin plugin = null;
        for (var p : plugins) {
            if (p.support(discoverRequest)) {
                plugin = p;
                break;
            }
        }
        // if no plugin support this device, return failure.
        if (plugin == null) {
            log.error("No supported plugin for request {}", discoverRequest);
            var respMessage = request.toFailureMessage(new ErrorResponseV1(
                    "collector.error.no-supported-plugin",
                    "No supported plugin.",
                    null
            ));
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(respMessage)));
            return "Can't find the plugin";
        }
        // get the result from plugin, use the result in response.
        try {
            var discoverResp = plugin.discover(discoverRequest);
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    request.toResponseMessage(discoverResp)
            )));
        } catch (Exception e) {
            log.warn("Discover from request {} failed.", request, e);
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                    request.toFailureMessage(new ErrorResponseV1(
                            "collector.exception.io",
                            "IO exception.",
                            null
                    ))
            )));
        }
        return "Device discover done";
    }
}
