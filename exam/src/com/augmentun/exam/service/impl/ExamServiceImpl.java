package com.augmentun.exam.service.impl;

import java.util.List;
import java.util.Map;

import com.augmentun.exam.dao.ExamDao;
import com.augmentun.exam.dao.PaperDao;
import com.augmentun.exam.dao.QuestionDao;
import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.ExamPagination;
import com.augmentun.exam.model.Paper;
import com.augmentun.exam.model.eto.ExamETO;
import com.augmentun.exam.service.ExamService;
import com.augmentun.exam.util.exam.ExamUtil;
import com.augmentun.exam.util.exam.ParamExceptionAddError;
import com.augmentun.exam.util.paper.PaperUtil;
public class ExamServiceImpl implements ExamService{
    private PaperDao paperDao;
    private ExamDao examDao;
    private QuestionDao questionDao;
    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public int createExam(ExamETO examETO) throws ParamterException {
        int examId = 0;
        ParamterException paramterException = new ParamterException();
        Map<String, Object> map = ParamExceptionAddError.getExceptionWithError(examETO, paramterException);
        ParamterException exception = (ParamterException)map.get("exception");
        if (exception.hasErrorMessage()) {
            throw paramterException;
        }
        Exam exam = (Exam)map.get("exam");
        int questionQty = questionDao.getQuestionQty("");
        int quantity = exam.getQuantity();
        //create exam
        if (quantity < questionQty) {
            exam.setIsDraft(0);
            examId = examDao.createExam(exam);
            //create paper
            Paper paper = new Paper();
            paper.setExamId(examId);
            List<Integer> questionIdArr = questionDao.findQuestionIdArr(quantity);
            paper.setQuestionIdArray(PaperUtil.idListToString(questionIdArr));
            paperDao.createPaper(paper);
        }

        return examId;
    }

    @Override
    public List<Exam> listExam(ExamPagination pagination) {
        List<Exam> examList = examDao.listExam(pagination);
        //htmlEncode
        //dateformat  YYYY-MM-DD hh:mm
        examList = ExamUtil.listHtmlEncode(examList);
        return examList;
    }

    @Override
    public int createDraftExam(ExamETO examETO) throws ParamterException {
        ParamterException paramterException = new ParamterException();
        Map<String, Object> map = ParamExceptionAddError.getExceptionWithError(examETO, paramterException);
        ParamterException exception = (ParamterException)map.get("exception");
        if (exception.hasErrorMessage()) {
            throw paramterException;
        }
        Exam exam = (Exam)map.get("exam");
        exam.setIsDraft(1);
        return examDao.createExam(exam);
    }

    @Override
    public int getPageCount(Map<String, String> conditions, int pageSize) {
        int quantity = examDao.getExamQuantity(conditions);
        //calculate pageCount
        return ExamPagination.calculatePageCount(quantity, pageSize);
    }

    @Override
    public Exam getAvailableExam(int examId) {
        //htmlEncode
        return ExamUtil.onehtmlEncode(examDao.getExamById(examId));
    }

    @Override
    public boolean editExam(ExamETO examETO, Exam originalExam) throws ParamterException {
        boolean flag = false;
        ParamterException paramterException = new ParamterException();
        Map<String, Object> map = ParamExceptionAddError.getExceptionWithError(examETO, paramterException);
        ParamterException exception = (ParamterException)map.get("exception");
        if (exception.hasErrorMessage()) {
            throw paramterException;
        }
        Exam exam = (Exam)map.get("exam");
        int examId = originalExam.getId();
        exam.setId(examId);
        int questionQty = questionDao.getQuestionQty("");
        int quantity = exam.getQuantity();
        //edit exam
        if (quantity < questionQty) {
            examDao.updateExam(exam);
            //select question
            List<Integer> questionIdArr = questionDao.findQuestionIdArr(quantity);
            Paper paper = new Paper();
            paper.setExamId(examId);
            paper.setQuestionIdArray(PaperUtil.idListToString(questionIdArr));
            //whether update Paper
            if (originalExam.getQuantity() == quantity) {
                return true;
            }

            //update or insert paper
            if (originalExam.getIsDraft() == 0) {
                paperDao.updateQuestionIdArr(paper);
            } else {
                paperDao.createPaper(paper);
            }
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean deleteExam(List<Integer> examIdList) {
        boolean deleteSuccess =false;
        int result = examDao.updateAvailable(examIdList);
        if (result > 0) {
            deleteSuccess = true;
        }
        return deleteSuccess;
    }
}
