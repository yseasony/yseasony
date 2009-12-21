package com.yy.action;

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
public class ResourceAction {

	@Autowired
	private IResourceSvc resourceSvc;

	@RequestMapping("/user/resourceSave.do")
	public void resourceSave(HttpServletRequest request,
			HttpServletResponse response, String value, String resourceType,String position) {

		Resource resource = new Resource();
		resource.setValue(value);
		resource.setResourceType(resourceType);
		resource.setPosition(Double.valueOf(position));

		this.resourceSvc.save(resource);
	}
	
	@RequestMapping("/user/resourceCreate.do")
	public ModelAndView userCreate(){
		
		HashMap<String,String> map = new HashMap<String,String>();
		String max = String.valueOf(resourceSvc.getMax());
		map.put("max", max);
		return new ModelAndView("Manager/resourceCreate",map);
	}

}
