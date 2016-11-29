package com.augmentun.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.augmentun.exam.Constants;
import com.augmentun.exam.model.Paper;
import com.augmentun.exam.model.Question;
import com.augmentun.exam.service.PaperService;
import com.augmentun.exam.service.QuestionService;
import com.augmentun.exam.util.StringUtil;
@Controller
@RequestMapping("page/paper")
public class PaperController extends BaseController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;

    public void setPaperService(PaperService paperService) {
        this.paperService = paperService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/queryPaper", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView queryPaper(String examId) {
        ModelAndView modelAndView = new ModelAndView();
        boolean isInt = StringUtil.parseIntSuccess(examId);
        if (!isInt) {
            modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE));;
            return modelAndView;
        }
        int id = Integer.parseInt(examId);
        //get paper
        Paper paper = paperService.getPaperByExamId(id);

        //find questionList
        List<Integer> questionIdList = new ArrayList<Integer>();
        String questionIdArray = paper.getQuestionIdArray();
        String[] questionIdArr = questionIdArray.split(",");
        for (String questionId : questionIdArr) {
            questionIdList.add(Integer.parseInt(questionId.trim()));
        }
        List<Question> questionList = questionService.findSeletedQuestion(questionIdList);
        modelAndView.addObject(Constants.PAPER, paper);
        modelAndView.addObject(Constants.QUESTION_LIST, questionList);
        modelAndView.setViewName(Constants.PAPER_QUERY_JSP);
        return modelAndView;
    }
}
