package com.yy.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.action.validate.ResourceValidator;
import com.yy.exception.MyException;
import com.yy.model.Resource;
import com.yy.service.IResourceSvc;
import com.yy.utils.HibernateWebUtils;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;
import com.yy.utils.Token;

@Controller
public class ResourceAction extends BaseAction<ResourceAction> {

	@Autowired
	private IResourceSvc resourceSvc;

	@RequestMapping(value = "/user/resourceSave.do", method = RequestMethod.POST)
	public void resourceSave(HttpServletRequest request,
			HttpServletResponse response, Resource resource,
			BindingResult result) {

		new ResourceValidator().validate(resource, result);
		if (result.hasErrors()) {
			writeOut(response, result.getFieldError().getCode());
			return;
		}

		try {
			this.resourceSvc.save(resource);
		} catch (MyException e) {
			writeOut(response, "保存失败！");
			return;
		}
		
	}

	@RequestMapping("/user/resourceCreate.do")
	public ModelAndView resourceCreate(HttpSession session) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		int max = resourceSvc.getMax("Resource");

		map.put("token", Token.getTokenString(session));
		map.put("max", max);
		return new ModelAndView("Pages/Manager/resourceCreate", map);
	}

	//@RequestMapping("/user/resourceList.do")
	public ModelAndView getResourceList(HttpServletRequest request,
			Integer pageNo, String orderBy, String order) {
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(request);

		Page<Resource> page = new Page<Resource>(10, pageNo, orderBy, order);
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}

		page = resourceSvc.searchResource(page, filters);
		return new ModelAndView("Pages/Manager/resourceList", "page", page);
	}

	@RequestMapping("/user/resourceList.do")
	public ModelAndView getResourceList() {
		return new ModelAndView("Pages/Manager/Ajax/resourceList");
	}

	@RequestMapping("/user/resourceDel.do")
	public String delResource(HttpServletRequest request, Long resourceId,
			Double position) {
		try {
			resourceSvc.delete(resourceId, position);
		} catch (MyException e) {
		}

		return "redirect:/user/resourceList.do";
	}

	@RequestMapping("/user/resourceEdit.do")
	public ModelAndView editResource(HttpSession session, Long resourceId) {
		Resource resource = resourceSvc.getById(resourceId);
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("resource", resource);
		map.put("token", Token.getTokenString(session));

		return new ModelAndView("Pages/Manager/resourceEdit", map);
	}
}
