package com.promise.collector.plugin.server.redfish;

import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicemonitor.sdk.dto.ComponentInstallState;
import com.promise.platform.devicemonitor.sdk.dto.DevicePowerState;
import com.promise.platform.devicemonitor.sdk.dto.HealthState;
import org.apache.http.HttpMessage;
import org.apache.http.client.methods.HttpGet;

import java.util.Base64;

public class RedfishUtil {
    /**
     * Get {@link DevicePowerState} from Redfish string expression.
     * @param powerState Redfish string expression.
     * @return {@link DevicePowerState}
     */
    public static DevicePowerState getPowerState(String powerState) {
        switch (powerState) {
            case "On":
            case "PoweringOff":
                return DevicePowerState.On;
            case "Off":
            case "PoweringOn":
                return DevicePowerState.Off;
            default:
                return DevicePowerState.Unknown;
        }
    }

    public static HealthState getHealthState(String health) {
        switch (health) {
            case "OK":
                return HealthState.OK;
            case "Warning":
                return HealthState.Warning;
            case "Critical":
                return HealthState.Critical;
            default:
                return HealthState.Unknown;
        }
    }

    public static ComponentInstallState getInstallState(String health) {
        switch (health) {
            case "Enabled":
                return ComponentInstallState.Inserted;
            case "Absent":
                return ComponentInstallState.Empty;
            default:
                return ComponentInstallState.Unknown;
        }
    }

    public static HttpGet getRequest(DiscoverDeviceRequestV1 request, String url) {
        var httpGet = new HttpGet("https://" + request.getAddress() + url);
        setCommonHeaders(httpGet, request.getUsername(), request.getPassword());
        return httpGet;
    }

    /**
     * Set common headers for the http message.
     *
     * @param message  the message to set.
     * @param username the user sending the message.
     * @param password the user's password.
     */
    public static void setCommonHeaders(HttpMessage message, String username, String password) {
        var userPass = username + ":" + password;
        var basicAuth = "Basic " + new String(Base64.getEncoder().encode(userPass.getBytes()));
        message.setHeader("Content-Type", "application/json");
        message.setHeader("Accept", "application/json");
        message.setHeader("Authorization", basicAuth);
    }
}
