package com.yy.utils;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLog<T> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterThrowing(pointcut = "execution(* com.yy.service..*.*(..))", throwing = "e")
	public void afterThrowing(Exception e) {
		logger.error(e.getMessage());
	}

}
