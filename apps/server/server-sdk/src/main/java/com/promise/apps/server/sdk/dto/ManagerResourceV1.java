package com.promise.apps.server.sdk.dto;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents response of manager resource.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ManagerResourceV1 extends BasicResourceResponseV1
{
    public ManagerSummaryV1 summary;
    public String firmawareVersion;
    public ManagerSettingsV1 settings;
    
    /**
     * Represents the manager settings in {@link ManagerResourceV1}
     *
     */
    @Data
    @NoArgsConstructor
    public static class ManagerSettingsV1 {
        public NtpSettingsV1 ntpSettings;
        public SnmpSettingsV1 snmpSettings;
        public SecuritySettingsV1 securitySettings;
        
        /**
         * Represents the NTP settings in {@link ManagerSettingsV1}
         *
         */
        @Data
        @NoArgsConstructor
        public static class NtpSettingsV1 {
            
        }
        
        /**
         * Represents the SNMP settings in {@link ManagerSettingsV1}
         *
         */
        @Data
        @NoArgsConstructor        
        public static class SnmpSettingsV1 {
            
        }
        
        /**
         * Represents the security settings in {@link ManagerSettingsV1}
         *
         */
        @Data
        @NoArgsConstructor
        public static class SecuritySettingsV1 {
            
        }
    }
}
