package com.promise.platform.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner
{
    @Value("${name}")
    private String name;

    public static void main(String args[])
    {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Override
    public void run(String... args)
            throws Exception
    {
        System.out.println(name);

    }
}
