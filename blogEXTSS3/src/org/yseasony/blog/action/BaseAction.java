package org.yseasony.blog.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.yseasony.blog.utils.Token;
import org.yseasony.blog.utils.string.YYStringUtils;

public class BaseAction<T> {
	
	protected Logger logger = Logger.getLogger(getClass());
	
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
		return YYStringUtils.isBlank(str);
	}
	
	protected boolean isBlank(String... str) {
		return YYStringUtils.isBlank(str);
	}

}
