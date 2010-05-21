package com.yy.utils;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yy.exception.MyException;

@Aspect
@Component
public class AopLog<T> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<T> entityClass;

	public AopLog() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	@AfterThrowing(pointcut = "execution(* com.yy.service..*.*(..))", throwing = "e")
	public void afterThrowing(MyException e) {
		logger.error(e.getMessage() + " cause by :" + e.getCause());
	}

}
