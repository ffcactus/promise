package com.promise.study.multitanent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiTenantApplication implements CommandLineRunner
{
    public static void main(String[] args)
    {
        SpringApplication.run(MultiTenantApplication.class, args);
    }

    @Override
    public void run(String... args)
            throws Exception
    {
    }
}
