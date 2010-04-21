package com.yy.ajax;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yy.model.Authority;
import com.yy.service.IAuthoritySvc;

@Controller
@RemoteProxy
public class AuthorityAjax extends BaseAjax<AuthorityAjax> {

	@Autowired
	private IAuthoritySvc authoritySvc;

	@RemoteMethod
	public boolean existAuthority(String column, String value) {

		if (isBlank(column, value)) {
			return false;
		}
		Authority authority = authoritySvc.exist(column, value);

		if (authority != null) {
			return false;
		}
		return false;
	}
}
