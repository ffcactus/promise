package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.ManagerResourceV1;
import com.promise.apps.server.sdk.dto.ManagerResourceV1.ManagerSettingsV1;
import com.promise.apps.server.sdk.dto.ManagerResourceV1.ManagerSettingsV1.NtpSettingsV1;
import com.promise.apps.server.sdk.dto.ManagerResourceV1.ManagerSettingsV1.SecuritySettingsV1;
import com.promise.apps.server.sdk.dto.ManagerResourceV1.ManagerSettingsV1.SnmpSettingsV1;
import com.promise.platform.sdk.model.BasicResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents manager resource of a server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ManagerResource extends BasicResource {
    public Server.ManagerSummary summary;
    public String firmawareVersion;
    public ManagerSettings settings;

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static ManagerResourceV1 toResponse(ManagerResource model) {
        var ret = new ManagerResourceV1();
        ret.summary = Server.ManagerSummary.toResponse(model.summary);
        ret.firmawareVersion = model.firmawareVersion;
        ret.settings = ManagerSettings.toResponse(model.settings);
        return ret;
    }

    /**
     * Represents the manager settings in {@link ManagerResource}
     */
    @Data
    @NoArgsConstructor
    public static class ManagerSettings {
        public NtpSettings ntpSettings;
        public SnmpSettings snmpSettings;
        public SecuritySettings securitySettings;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static ManagerSettingsV1 toResponse(ManagerSettings model) {
            var ret = new ManagerSettingsV1();
            ret.ntpSettings = NtpSettings.toResponse(model.ntpSettings);
            ret.snmpSettings = SnmpSettings.toResponse(model.snmpSettings);
            ret.securitySettings = SecuritySettings.toResponse(model.securitySettings);
            return ret;
        }

        /**
         * Represents the NTP settings in {@link ManagerSettings}
         */
        @Data
        @NoArgsConstructor
        public static class NtpSettings {

            /**
             * Convert model to response DTO.
             *
             * @param model The model object.
             * @return The response DTO object.
             */
            public static NtpSettingsV1 toResponse(NtpSettings model) {
                var ret = new NtpSettingsV1();
                return ret;
            }

        }

        /**
         * Represents the SNMP settings in {@link ManagerSettings}
         */
        @Data
        @NoArgsConstructor
        public static class SnmpSettings {
            /**
             * Convert model to response DTO.
             *
             * @param model The model object.
             * @return The response DTO object.
             */
            public static SnmpSettingsV1 toResponse(SnmpSettings model) {
                var ret = new SnmpSettingsV1();
                return ret;
            }
        }

        /**
         * Represents the security settings in {@link ManagerSettings}
         */
        @Data
        @NoArgsConstructor
        public static class SecuritySettings {
            /**
             * Convert model to response DTO.
             *
             * @param model The model object.
             * @return The response DTO object.
             */
            public static SecuritySettingsV1 toResponse(SecuritySettings model) {
                var ret = new SecuritySettingsV1();
                return ret;
            }

        }
    }
}
