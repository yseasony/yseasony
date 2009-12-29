package com.yy.ajax;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yy.model.Resource;
import com.yy.service.IResourceSvc;


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
			map.put("exist", exist);
			modelAndView.addAllObjects(map);
			return modelAndView;
		}

		Resource resource = this.resourceSvc.resourceExist(resourceType.trim());

		if (resource != null) {
			map.put("exist", exist);
			modelAndView.addAllObjects(map);
			return modelAndView;
		}
		exist = true;
		map.put("exist", exist);
		modelAndView.addAllObjects(map);
		return modelAndView;

	}

}
