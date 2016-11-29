package com.augmentun.exam.service.impl;

import java.util.List;

import com.augmentun.exam.dao.PaperDao;
import com.augmentun.exam.dao.QuestionDao;
import com.augmentun.exam.model.Paper;
import com.augmentun.exam.service.PaperService;
import com.augmentun.exam.util.paper.PaperUtil;

public class PaperServiceImpl implements PaperService{
    private PaperDao paperDao;
    private QuestionDao questionDao;

    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void createPaper(int questionQuantity, int examId) {
        Paper paper = new Paper();
        paper.setExamId(examId);
        //get useful questions
        List<Integer> questionIdArr = questionDao.findQuestionIdArr(questionQuantity);
        String idArr = PaperUtil.idListToString(questionIdArr);
        paper.setQuestionIdArray(idArr);
        paperDao.createPaper(paper);
    }

    @Override
    public Paper getPaperByExamId(int examId) {
        return paperDao.getPaperByExamId(examId);
    }

}
