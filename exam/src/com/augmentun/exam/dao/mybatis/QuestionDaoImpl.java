package com.augmentun.exam.dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentun.exam.dao.QuestionDao;
import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.Question;

public class QuestionDaoImpl extends SqlSessionDaoSupport implements QuestionDao {
    private static final String CLASS_NAME = Question.class.getName();

    @Override
    public List<Question> findQuestions(Pagination pagination) {
        return getSqlSession().selectList(CLASS_NAME + ".findQuestions", pagination);
    }

    @Override
    public Integer getQuestionQty(String keyword) {
        return getSqlSession().selectOne(CLASS_NAME + ".getQuestionQty", keyword);
    }

    @Override
    public int createQuestion(Question question) {
       getSqlSession().insert(CLASS_NAME + ".createQuestion", question);
       return question.getId();
    }

    @Override
    public Question getQuestionById(int questionId) {
        Question question = null;
        question = getSqlSession().selectOne(CLASS_NAME + ".getQuestionById", questionId);
        return question;
    }

    @Override
    public int updateQuestionById(List<String> deleteList) {
        return getSqlSession().delete(CLASS_NAME + ".updateQuestionById", deleteList);
    }

    @Override
    public int getQuestionQtyById(String questionNumber) {
        return getSqlSession().selectOne(CLASS_NAME + ".getQuestionQtyById", questionNumber);
    }

    @Override
    public List<Integer> findQuestionIdArr(int QuestionNumber) {
        return getSqlSession().selectList(CLASS_NAME + ".findQuestionIdArr", QuestionNumber);
    }

    @Override
    public List<Question> findSeletedQuestion(List<Integer> questionIdList) {
        return getSqlSession().selectList(CLASS_NAME + ".findSeletedQuestion", questionIdList);
    }

}
