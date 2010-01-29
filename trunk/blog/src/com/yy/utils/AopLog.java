package com.yy.utils;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLog<T> {

	@Pointcut("execution(* com.yy.service..*.*(..))")
	public void doMain() {
	}
	
	@AfterThrowing(value="doMain()" ,throwing="e")
	public void afterThrowing(Exception e) {
		System.out.println("do afterThrowing");
	}
	
}
