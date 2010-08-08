package com.yy.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import com.yy.utils.MyStringUtils;
import com.yy.utils.Token;

public class BaseAction<T> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected void writeOut(HttpServletResponse response, String value) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(value);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error("response error", e);
		}
	}
	
	protected void setErrorMsgWithToken(HttpServletRequest request, String value){
		request.setAttribute("error", value);
		request.setAttribute("token", Token.getTokenString(request.getSession()));
	}
	
	protected void setErrorMsgWithToken(HttpServletRequest request, BindingResult result){
		request.setAttribute("error", result.getFieldError().getCode());
		request.setAttribute("token", Token.getTokenString(request.getSession()));
	}
	
	protected void setErrorMsg(HttpServletRequest request, String value) {
		request.setAttribute("error", value);
	}
	
	protected boolean isBlank(String str) {
		return MyStringUtils.isBlank(str);
	}
	
	protected boolean isBlank(String... str) {
		return MyStringUtils.isBlank(str);
	}

}
