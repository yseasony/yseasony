package com.yy.ajax;

import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yy.exception.MyException;
import com.yy.lang.utils.HibernateWebUtils;
import com.yy.lang.utils.Page;
import com.yy.lang.utils.PropertyFilter;
import com.yy.model.Role;
import com.yy.service.IRoleSvc;

@Controller
@RemoteProxy
public class RoleAjax extends BaseAjax<RoleAjax>{
	
	@Autowired
	private IRoleSvc roleSvc;
	
	@RemoteMethod
	public boolean existRole(String value) {
		try {
			return roleSvc.exist("value", value);
		} catch (MyException e) {
			return false;
		}
	}
	
	@RemoteMethod
	public Page<Role> getRoleList(
			Integer pageNo, String orderBy, String order,
			HashMap<String, String> map) {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFiltersAjax(map);
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
