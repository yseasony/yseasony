package org.yseasony.blog.ajax;

import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.yseasony.blog.entity.Authority;
import org.yseasony.blog.exception.MyException;
import org.yseasony.blog.service.AuthoritySvc;
import org.yseasony.blog.utils.HibernateWebUtils;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;


@Component
@RemoteProxy
public class AuthorityAjax extends BaseAjax<AuthorityAjax> {

	@Autowired
	private AuthoritySvc authoritySvc;

	@RemoteMethod
	public boolean existDisplayName(String value) {
		try {
			return authoritySvc.exist("displayName", value);
		} catch (MyException e) {
			return false;
		}
	}
	
	@RemoteMethod
	public boolean existName(String value) {
		try {
			return authoritySvc.exist("name", value);
		} catch (MyException e) {
			return false;
		}
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
