package com.yy.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yy.model.Resource;
import com.yy.service.IResourceSvc;
import com.yy.utils.HibernateWebUtils;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

@Controller
public class ResourceAjax {

	@Autowired
	private IResourceSvc resourceSvc;

	@RequestMapping("/resourceExist.ajax")
	public ModelAndView exist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<Object, Object> map = new HashMap<Object, Object>();

		ModelAndView modelAndView = new ModelAndView("jsonView");

		boolean exist = false;

		String resourceType = request.getParameter("resourceType");

		if (resourceType == null || resourceType.trim().length() <= 0) {
			map.put("exist", true);
			modelAndView.addAllObjects(map);
			return modelAndView;
		}

		Resource resource = this.resourceSvc
				.exist("value", resourceType.trim());

		if (resource != null) {
			map.put("exist", true);
			modelAndView.addAllObjects(map);
			return modelAndView;
		}
		exist = true;
		map.put("exist", exist);
		modelAndView.addAllObjects(map);
		return modelAndView;

	}
	
	@RequestMapping("/user/resourceList.ajax")
	public ModelAndView getResourceList(HttpServletRequest request,
			Integer pageNo, String orderBy, String order,String filter_LIKES_resourceName) {
		
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(request);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");

		Page<Resource> page = new Page<Resource>(10, pageNo, orderBy, order);
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		page = resourceSvc.getPage(page,filters);
		
		JSONObject jsonObject = JSONObject.fromObject(page);
		
		map.put("page", jsonObject);

		modelAndView.addAllObjects(map);
		return modelAndView;
	}

}
