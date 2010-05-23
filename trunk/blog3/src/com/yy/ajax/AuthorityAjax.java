package com.yy.ajax;

import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yy.exception.MyException;
import com.yy.model.Authority;
import com.yy.service.IAuthoritySvc;
import com.yy.utils.HibernateWebUtils;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

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
		Authority authority = null;
		try {
			authority = authoritySvc.exist(column, value);
		} catch (MyException e) {
			return false;
		}

		if (authority != null) {
			return false;
		}
		return true;
	}
	
	@RemoteMethod
	public Page<Authority> getAuthorityList(
			Integer pageNo, String orderBy, String order,
			HashMap<String, String> map) {
		List<PropertyFilter> filters = null;
		try {
			filters = HibernateWebUtils.buildPropertyFiltersAjax(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Page<Authority> page = new Page<Authority>(10, pageNo, orderBy, order);
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		
		page = authoritySvc.getPage(page, filters);
		return page;
	}
}
