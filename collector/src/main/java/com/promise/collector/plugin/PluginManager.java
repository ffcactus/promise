package com.promise.collector.plugin;

import com.promise.collector.model.Device;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PluginManager {

    private static final Logger log = LoggerFactory.getLogger(PluginManager.class);

    private final Map<String, List<Plugin>> plugins = new HashMap<>();

    public void register(Plugin plugin) {
        if (plugins.containsKey(plugin.getVendor())) {
            plugins.get(plugin.getVendor()).add(plugin);
        } else {
            plugins.put(plugin.getVendor(), List.of(plugin));
        }
        log.info("A plugin is registered {}", plugin);
    }

    public List<Plugin> getPlugin(DiscoverDeviceRequestV1 request) {
        return plugins.get(request.getVendor()).stream().filter(
                p -> p.getVendor().equals(request.getVendor())
        ).collect(Collectors.toList());
    }

    public Plugin create(Device device) {
        return null;
    }
}
