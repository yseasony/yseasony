package org.yseasony.blog.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yseasony.blog.service.impl.TxService;

@Controller
public class TxAction {

	@Autowired
	private TxService txService;

	@RequestMapping("/t.do")
	public ModelAndView t() {
		txService.t();
		return new ModelAndView("ok");
	}
}
