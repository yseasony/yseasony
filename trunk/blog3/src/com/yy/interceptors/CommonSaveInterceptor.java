package com.yy.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yy.action.BaseAction;
import com.yy.utils.Token;

@Component
public class CommonSaveInterceptor extends BaseAction<CommonSaveInterceptor> implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!Token.isTokenStringValid(request.getParameter("token"), request.getSession())) {
			writeOut(response, "请勿重复提交");
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("after----------");
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post----------");
		
	}

}
