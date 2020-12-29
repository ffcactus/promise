package com.promise.apps.server;

import com.promise.apps.server.repository.ServerRepository;
import com.promise.platform.sdk.client.TaskServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.promise.platform.sdk"})
@EnableFeignClients(basePackages = {"com.promise.platform"})
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ServerApplication implements CommandLineRunner {
    @Autowired
    private TaskServiceClient taskClient;

    @Value("${self.db.recreate}")
    private Boolean reCreateDB;

    @Autowired
    private ServerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args)
            throws Exception {
        try {
            var task = taskClient.getTaskById("1");
            System.out.println(task);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        if (reCreateDB) {
            repository.deleteAll();
        }
    }
}
