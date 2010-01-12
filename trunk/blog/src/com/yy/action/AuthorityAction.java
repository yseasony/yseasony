package com.yy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.yy.model.Authority;
import com.yy.service.impl.AuthoritySvc;

@Controller
public class AuthorityAction {
	
	private AuthoritySvc authoritySvc;
	
	public void authoritySave(HttpServletRequest request,
			HttpServletResponse response,Authority authority){
		
		authoritySvc.save(authority);
	}

}
