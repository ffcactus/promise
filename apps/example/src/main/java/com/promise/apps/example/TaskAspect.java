package com.promise.apps.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TaskAspect
{

    @Around("@annotation(com.promise.apps.example.Taskstep)")
    public Object aroundTaskStep(ProceedingJoinPoint joinPoint)
            throws Throwable
    {
        Object ret = null;
        try
        {
            System.out.println("Start exectuion advise");
            ret = joinPoint.proceed();

        }
        catch (final Throwable throwable)
        {
            throw throwable;
        }
        finally
        {
            System.out.println("End exectuion advise");
        }
        return ret;
    }
}
