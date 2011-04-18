package org.yseasony.acg.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLog {
	/**
	 * 本地日志记录
	 */
	protected final Logger logger = Logger.getLogger(getClass());

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
