package com.augmentun.exam.dao;

import java.util.List;

import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.Question;

public interface QuestionDao {

    public List<Question> findQuestions(Pagination pagination);

    public List<Question> findSeletedQuestion(List<Integer> questionIdList);

    public List<Integer> findQuestionIdArr(int QuestionNumber);

    public int getQuestionQtyById(String questionNumber);

    public Integer getQuestionQty(String keyword);

    public Question getQuestionById(int question_id);

    public int createQuestion(Question question);

    public int updateQuestionById(List<String> deleteList);

}
