package com.yy.utils;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLog<T> {

	@AfterThrowing(pointcut="execution(* com.yy.service..*.*(..))",throwing="e")
	public void afterThrowing(Exception e) {
		System.out.println("do afterThrowing");
	}
	
}
