package org.yseasony.exam.action.admin.exam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.exam.action.BaseAction;
import org.yseasony.exam.entity.ExaminationPager;
import org.yseasony.exam.services.ExamSvcImpl;
import org.yseasony.exam.utils.Page;

@Controller
@RequestMapping("/admin/exam")
public class ExaminationPagerAction extends BaseAction{

    @Autowired
    private ExamSvcImpl examSvcImpl;

    @RequestMapping("/examinationPagerList")
    @ResponseBody
    public Page<ExaminationPager> examinationPagerList(Integer pageNo, HttpServletRequest request) {
        Page<ExaminationPager> page = new Page<ExaminationPager>(10);
        page.setPageNo(pageNo);
        return examSvcImpl.getExaminationPagerPage(page);
    }
    
    @RequestMapping("/examinationPagerPage")
    public String examinationPagerPage() {
        return "admin/exam/examinationPager";
    }
    
    @RequestMapping("/addExaminationPager")
    public String addExaminationPager(ExaminationPager examinationPager){
        return "admin/exam/examinationPager";
    }
}
