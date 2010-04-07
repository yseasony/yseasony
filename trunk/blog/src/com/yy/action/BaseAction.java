package com.yy.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yy.utils.MyStringUtils;

public class BaseAction<T> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected void writeOut(HttpServletResponse response, String value) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(value);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error("response出错", e.getStackTrace());
		}
	}
	
	protected boolean isBlank(String str) {
		return MyStringUtils.isBlank(str);
	}
	
	protected boolean isBlank(String... str) {
		return MyStringUtils.isBlank(str);
	}

}