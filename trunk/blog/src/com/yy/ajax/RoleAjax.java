package com.yy.ajax;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yy.exception.MyException;
import com.yy.model.Role;
import com.yy.service.IRoleSvc;

@Controller
@RemoteProxy
public class RoleAjax extends BaseAjax<RoleAjax>{
	
	@Autowired
	private IRoleSvc roleSvc;
	
	@RemoteMethod
	public boolean existRole(String value) {
		if (isBlank(value)) {
			return false;
		}
		Role role = null;
		try {
			role = this.roleSvc.exist("value", value.trim());
		} catch (MyException e) {
			return false;
		}

		if (role != null) {
			return false;
		}
		return true;
	}
}
