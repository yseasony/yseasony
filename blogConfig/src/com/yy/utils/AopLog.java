package com.yy.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.yy.exception.MyException;

@Aspect
@Component
public class AopLog<T> {
	
	private Logger logger = Logger.getLogger(getClass());

	@AfterThrowing(pointcut = "execution(* com.yy.service..*.*(..))", throwing = "e")
	public void afterThrowing(MyException e) {
		logger.error(e.getMessage() + " cause by :" + e.getCause());
	}

}
