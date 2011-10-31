package org.yseasony.exam.services;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.yseasony.exam.dao.ExaminationPagerDao;
import org.yseasony.exam.dao.QuestionBankDao;
import org.yseasony.exam.entity.ExaminationPager;
import org.yseasony.exam.entity.QuestionBank;
import org.yseasony.exam.utils.Page;
import org.yseasony.utils.POIUtils;

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
    public ExaminationPager saveExaminationPager(ExaminationPager examinationPager, MultipartFile file){
        examinationPagerDao.insert(examinationPager);
        if (file.getSize() != 0) {
            try {
                POIUtils poiUtils = new POIUtils(file.getInputStream());
                HSSFSheet sheet = poiUtils.getSheet();
                for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                    HSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        QuestionBank questionBank = new QuestionBank();
                        questionBank.setExId(examinationPager.getExId());
                        //题型
                        HSSFCell cell = row.getCell(0); 
                        if (cell != null) {
                            questionBank.setQtId((int)cell.getNumericCellValue());
                        }
                        //标题
                        cell = row.getCell(1); 
                        if (cell != null) {
                            questionBank.setTitle(cell.getStringCellValue());
                        }
                        //问题
                        cell = row.getCell(2); 
                        if (cell != null) {
                            questionBank.setQuestion(cell.getStringCellValue());
                        }
                        //答案
                        cell = row.getCell(3); 
                        if (cell != null) {
                            questionBank.setAnswer(cell.getStringCellValue());
                        }
                        //分数
                        cell = row.getCell(4); 
                        if (cell != null) {
                            questionBank.setScore((int)cell.getNumericCellValue());
                        }
                        System.out.println(questionBank.getQuestion());
                        questionBankDao.insert(questionBank);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return examinationPager;
    }
    
    @Transactional
    public void deleteExaminationPager(Integer id){
    	examinationPagerDao.delete(id);
    }
    
}
