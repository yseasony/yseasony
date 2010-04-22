package com.yy.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yy.exception.MyException;
import com.yy.model.Resource;
import com.yy.service.IResourceSvc;
import com.yy.utils.HibernateWebUtils;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

@Controller
@RemoteProxy
public class ResourceAjax extends BaseAjax<ResourceAjax> {

	@Autowired
	private IResourceSvc resourceSvc;

	@RemoteMethod
	public boolean existResource(String value) {
		if (isBlank(value)) {
			return false;
		}
		Resource resource = null;
		try {
			resource = this.resourceSvc.exist("value", value.trim());
		} catch (MyException e) {
			return false;
		}

		if (resource != null) {
			return false;
		}
		return true;
	}

	@RemoteMethod
	public Page<Resource> getResourceList(HttpServletRequest request,
			Integer pageNo, String orderBy, String order,
			HashMap<String, String> map) {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFiltersAjax(map);
		Page<Resource> page = new Page<Resource>(10, pageNo, orderBy, order);
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = resourceSvc.getPage(page, filters);
		return page;
	}

}
