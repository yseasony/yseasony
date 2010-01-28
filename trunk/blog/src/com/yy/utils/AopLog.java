package com.yy.utils;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yy.service.exception.ServiceException;

@Aspect
@Component
public class AopLog<T> {

	@Pointcut("execution(* com.yy.service..*.*(..))")
	public void doMain() {
	}

	@Before(value="doMain()")
	public void vefore() {
		System.out.println("do");
	}
	
	@AfterThrowing(value="doMain()" ,throwing="e")
	public void afterThrowing(Exception e) {
		System.out.println("do afterThrowing");
	}
	
	@After(value="doMain()")
	public void after() {
		System.out.println("do after");
	}
}
