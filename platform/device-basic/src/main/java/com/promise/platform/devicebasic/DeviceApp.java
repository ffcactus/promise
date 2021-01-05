package com.promise.platform.devicebasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.promise"})
public class DeviceApp {
    public static void main(String[] args) {
        SpringApplication.run(DeviceApp.class, args);
    }
}
