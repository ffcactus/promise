package com.promise.apps.example;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class RefreshOperation implements Ordered {
	public void Do() {
		step1();
		step2();
	}
	
	@Taskstep
	public void step1() {
		System.out.println("Step1");
	}
	
	@Taskstep
	public void step2() {
		System.out.println("Step2");
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
}
