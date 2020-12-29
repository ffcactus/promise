package com.promise.platform.devicemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.promise"})
public class DeviceMonitorApp {
    public static void main(String[] args) {
        SpringApplication.run(DeviceMonitorApp.class, args);
    }
}
