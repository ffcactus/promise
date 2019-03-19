package com.promise.apps.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskAspect {

	@Before("@annotation(com.promise.apps.example.Taskstep)")
	public Object aroundTaskStep() throws Throwable {
		Object ret = null;
		System.out.println("Start annotation advise");

		return ret;
	}
	
	@Around("execution(public void com.promise.apps.example.RefreshOperation.*(..))")
	public Object arount(ProceedingJoinPoint joinPoint) throws Throwable {
		Object ret = null;
		try {
			System.out.println("Start exectuion advise");
			ret = joinPoint.proceed();

		} catch (Throwable throwable) {
			throw throwable;
		} finally {
			System.out.println("End exectuion advise");
		}
		return ret;
	}
}
