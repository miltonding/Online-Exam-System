package com.augmentun.exam.block;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.augmentun.exam.Constants;
import com.augmentun.exam.common.BlockAbstract;
import com.augmentun.exam.model.Question;
import com.augmentun.exam.service.QuestionService;

public class QueryQuestionBlock extends BlockAbstract{
    private QuestionService questionService;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    protected void execute(PageContext pageContext) {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        String id = request.getParameter("id");
        Question question = questionService.queryQuestionById(Integer.parseInt(id.trim()));
        request.setAttribute(Constants.QUESTION, question);
    }

}
