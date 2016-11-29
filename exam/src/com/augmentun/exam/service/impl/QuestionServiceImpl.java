package com.augmentun.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.augmentun.exam.dao.QuestionDao;
import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.Question;
import com.augmentun.exam.service.QuestionService;
import com.augmentun.exam.util.question.ParamExceptionAddError;
import com.augmentun.exam.util.question.QuestionUtil;

public class QuestionServiceImpl implements QuestionService{
    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> findQuestionsPaging(Pagination pagination) {
        List<Question> questionList = questionDao.findQuestions(pagination);
        //Question field htmlEncode
        if (questionList.size() != 0) {
            List<Question> htmlQuestionList = new ArrayList<Question>();
            for (Question question : questionList) {
                Question htmlQuestion = QuestionUtil.htmlEncode(question);
                htmlQuestionList.add(htmlQuestion);
            }
            questionList = htmlQuestionList;
        }
        return questionList;
    }

    @Override
    public int getQuestionQtyByKeyword(String keyword) {
        return questionDao.getQuestionQty(keyword);
    }

    @Override
    public int createQuestion(Question question) throws ParamterException {
        int result = 0;
        //validate parameters
        ParamterException paramterException = new ParamterException();
        ParamExceptionAddError.getExceptionWithError(question, paramterException);
        if(paramterException.hasErrorMessage()) {
            throw paramterException;
        }
        result = questionDao.createQuestion(question);
        return result;
    }

    @Override
    public Question queryQuestionById(int questionId) {
        Question question = null;
        question = questionDao.getQuestionById(questionId);
        //htmlEncode
        question = QuestionUtil.htmlEncode(question);
        return question;
    }

    @Override
    public int editQuestion(Question question) throws ParamterException {
        int result = 0;
        //validate parameters
        ParamterException paramterException = new ParamterException();
        ParamExceptionAddError.getExceptionWithError(question, paramterException);
        if(paramterException.hasErrorMessage()) {
            throw paramterException;
        }
        //update is_available
        List<String> list = new ArrayList<String>();
        list.add(String.valueOf(question.getId()));
        questionDao.updateQuestionById(list);
        //create new question
        question.setIsAvailable(1);
        result = questionDao.createQuestion(question);
        return result;
    }

    @Override
    public boolean updateQuestionAvailable(List<String> deleteList) {
        boolean updateSuccess = false;
        int result = questionDao.updateQuestionById(deleteList);
        if (result > 0) {
            updateSuccess = true;
        }
        return updateSuccess;
    }

    @Override
    public boolean getQuestionQtyById(String questionNumber) {
        boolean result = true;
        int count = questionDao.getQuestionQtyById(questionNumber);
        if (count > 0) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Question> findSeletedQuestion(List<Integer> questionIdList) {

        return questionDao.findSeletedQuestion(questionIdList);
    }
}
