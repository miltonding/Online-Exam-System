package com.augmentun.exam.service;

import java.util.List;

import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.Question;

public interface QuestionService {

    public List<Question> findQuestionsPaging(Pagination pagination);

    public int getQuestionQtyByKeyword(String keyword);

    public int createQuestion(Question question) throws ParamterException;

    public Question queryQuestionById(int questionId);

    public int editQuestion(Question question) throws ParamterException;

    public boolean updateQuestionAvailable(List<String> deleteList);

    public boolean getQuestionQtyById(String questionNumber);

    public List<Question> findSeletedQuestion(List<Integer> questionIdList);

}
