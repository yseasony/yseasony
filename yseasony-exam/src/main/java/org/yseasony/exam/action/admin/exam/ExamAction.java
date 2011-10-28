package org.yseasony.exam.action.admin.exam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.exam.entity.QuestionBank;
import org.yseasony.exam.services.ExamSvcImpl;
import org.yseasony.exam.utils.Page;

@Controller
@RequestMapping("/admin/exam")
public class ExamAction {

    @Autowired
    private ExamSvcImpl examSvcImpl;

    @RequestMapping("/questionBankList")
    @ResponseBody
    public Page<QuestionBank> questionBankList(Integer pageNo, HttpServletRequest request) {
        Page<QuestionBank> page = new Page<QuestionBank>(10);
        page.setPageNo(pageNo);
        return examSvcImpl.getQuestionBankPage(page);
    }
    
    @RequestMapping("/questionBankPage")
    public String questionBankPage() {
        return "admin/exam/questionBank";
    }
    
}
