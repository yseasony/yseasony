package org.yseasony.acg.log;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLog {
	/**
	 * 本地日志记录
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 拦截异常，记录
	 * 
	 * @param e
	 */
	@AfterThrowing(pointcut = "execution(* org.yseasony.acg.services.*.*(..))", throwing = "e")
	public void afterThrowing(RuntimeException e) {
		logger.error(e.getMessage(), e);
	}
}
