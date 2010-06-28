package com.yy.ajax;

import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yy.exception.MyException;
import com.yy.model.Role;
import com.yy.service.IRoleSvc;
import com.yy.utils.HibernateWebUtils;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

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
	
	@RemoteMethod
	public Page<Role> getRoleList(
			Integer pageNo, String orderBy, String order,
			HashMap<String, String> map) {
		List<PropertyFilter> filters = null;
		try {
			filters = HibernateWebUtils.buildPropertyFiltersAjax(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Page<Role> page = new Page<Role>(10, pageNo, orderBy, order);
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		
		page = roleSvc.getPage(page, filters);
		return page;
	}
}
