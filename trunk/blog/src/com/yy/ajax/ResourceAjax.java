package com.yy.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public class ResourceAjax extends BaseAjax<ResourceAjax>{

	@Autowired
	private IResourceSvc resourceSvc;

	@RequestMapping("/resourceExist.ajax")
	public ModelAndView exist(String value){

		ModelAndView modelAndView = new ModelAndView("jsonView");
		boolean exist = false;
		modelAndView.addObject("exist",exist);
		
		if (isBlank(value)) {
			return modelAndView;
		}

		Resource resource = this.resourceSvc.exist("value", value.trim());
		if (resource != null) {
			return modelAndView;
		}
		
		exist = true;
		modelAndView.addObject("exist",exist);
		return modelAndView;

	}
	
	@RequestMapping("/user/resourceList.ajax")
	public ModelAndView getResourceList(HttpServletRequest request,
			Integer pageNo, String orderBy, String order,String filter_LIKES_resourceName) {
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);

		Page<Resource> page = new Page<Resource>(10, pageNo, orderBy, order);
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		
		page = resourceSvc.getPage(page,filters);
		JSONObject jsonObject = JSONObject.fromObject(page);
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("page", jsonObject);
		return modelAndView;
	}

}
