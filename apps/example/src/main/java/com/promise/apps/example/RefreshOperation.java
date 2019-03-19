package com.promise.apps.example;

import org.springframework.stereotype.Service;

@Service
public class RefreshOperation
{
    public void Do()
    {
        step1();
        step2();
    }

    @Taskstep(weight = 1)
    public void step1()
    {
        System.out.println("Step1");
    }

    @Taskstep(weight = 2)
    public void step2()
    {
        System.out.println("Step2");
    }
}
