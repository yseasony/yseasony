package org.yseasony.blog.ajax;

import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.yseasony.blog.entity.Role;
import org.yseasony.blog.exception.MyException;
import org.yseasony.blog.service.RoleSvc;
import org.yseasony.blog.utils.HibernateWebUtils;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;


@Component
@RemoteProxy
public class RoleAjax extends BaseAjax<RoleAjax>{
	
	@Autowired
	private RoleSvc roleSvc;
	
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
