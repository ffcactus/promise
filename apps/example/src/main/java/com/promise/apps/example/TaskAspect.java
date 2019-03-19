package com.promise.apps.example;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
        int weight = 0;
        try
        {
            final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            final Method method = signature.getMethod();
            final Taskstep annotation = method.getAnnotation(Taskstep.class);
            weight = annotation.weight();
            System.out.printf("Task step weight %d begin.\n", weight);
            ret = joinPoint.proceed();

        }
        catch (final Throwable throwable)
        {
            throw throwable;
        }
        finally
        {
            System.out.printf("Task step weight %d end.\n", weight);
        }
        return ret;
    }
}
