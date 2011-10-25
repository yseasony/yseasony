package org.yseasony.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.exam.dao.ExaminationPagerDao;
import org.yseasony.exam.dao.QuestionBankDao;
import org.yseasony.exam.entity.ExaminationPager;
import org.yseasony.exam.entity.QuestionBank;
import org.yseasony.exam.utils.Page;

@Service
public class ExamSvcImpl {

    @Autowired
    private QuestionBankDao questionBankDao;
    @Autowired
    private ExaminationPagerDao examinationPagerDao;
    
    @Transactional
    public QuestionBank saveQuestionBank(QuestionBank questionBank){
        return questionBankDao.insert(questionBank);
    }
    
    /**
     * 题库
     * @param page
     * @return ExaminationPagerDao
     */
    @Transactional(readOnly = true)
    public Page<QuestionBank> getQuestionBankPage(Page<QuestionBank> page){
        return questionBankDao.page(page);
    }
    
    /**
     * 试卷信息
     * @param page
     * @return Page<ExaminationPager>
     */
    @Transactional(readOnly = true)
    public Page<ExaminationPager> getExaminationPagerPage(Page<ExaminationPager> page){
        return examinationPagerDao.page(page);
    }
    
    @Transactional
    public ExaminationPager saveExaminationPager(ExaminationPager examinationPager){
        return examinationPagerDao.insert(examinationPager);
    }
    
}
