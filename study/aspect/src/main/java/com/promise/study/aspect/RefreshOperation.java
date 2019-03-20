package com.promise.study.aspect;

import org.springframework.stereotype.Service;

import com.promise.study.aspect.task.annotation.TaskStep;

@Service
public class RefreshOperation
{
    public void Do()
    {
        step1();
        step2();
    }

    @TaskStep(weight = 1)
    public void step1()
    {
        System.out.println("Step1");
    }

    @TaskStep(weight = 2)
    public void step2()
    {
        System.out.println("Step2");
    }
}
