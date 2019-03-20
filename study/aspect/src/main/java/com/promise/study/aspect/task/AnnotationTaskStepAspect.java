package com.promise.study.aspect.task;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.promise.study.aspect.task.annotation.TaskStep;

@Aspect
@Component
public class AnnotationTaskStepAspect
{

    @Around("@annotation(com.promise.study.aspect.task.annotation.TaskStep)")
    public Object aroundTaskStep(ProceedingJoinPoint joinPoint)
            throws Throwable
    {
        Object ret = null;
        int weight = 0;
        try
        {
            final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            final Method method = signature.getMethod();
            final TaskStep annotation = method.getAnnotation(TaskStep.class);
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
