package com.augmentun.exam.dao.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentun.exam.dao.PaperDao;
import com.augmentun.exam.model.Paper;

public class PaperDaoImpl extends SqlSessionDaoSupport implements PaperDao{
    private static final String CLASS_NAME = Paper.class.getName();

    @Override
    public int createPaper(Paper paper) {
       getSqlSession().insert(CLASS_NAME + ".createPaper", paper);
       return paper.getId();
    }

    @Override
    public int updateQuestionIdArr(Paper paper) {
        return  getSqlSession().update(CLASS_NAME + ".updateQuestionIdArr", paper);
    }

    @Override
    public int getPaperQuantity(int examId) {
        return getSqlSession().selectOne(CLASS_NAME + ".getPaperQuantity", examId);
    }

    @Override
    public Paper getPaperByExamId(int examId) {
        return getSqlSession().selectOne(CLASS_NAME + ".getPaperByExamId", examId);
    }

}
