package com.huawei.skywalker.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.huawei.skywalker.server.repository.ServerRepository;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner
{
    //    @Autowired
    //    @Lazy
    //    private EurekaClient eurekaClient;

    @Value("${db.recreate}")
    private Boolean reCreateDB;

    @Autowired
    private ServerRepository repository;

    public static void main(String[] args)
    {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args)
            throws Exception
    {
        if (reCreateDB)
        {
            repository.deleteAll();
        }
    }
}
