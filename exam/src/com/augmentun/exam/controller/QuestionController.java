package com.augmentun.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.augmentun.exam.Constants;
import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.Question;
import com.augmentun.exam.service.QuestionService;
import com.augmentun.exam.util.StringUtil;
import com.augmentun.exam.util.question.QuestionUtil;
@Controller
@RequestMapping("page/question")
public class QuestionController extends BaseController{
    @Autowired
    private QuestionService questionService;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/questionList", method = RequestMethod.GET)
    public ModelAndView questionList(
                                     @RequestParam(value = "currentPage", defaultValue = "1") String stringCurrentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10") String stringPageSize,
                                     @RequestParam(value = "order", defaultValue = "ASC") String order,
                                     @RequestParam(value = "keyword", defaultValue = "") String keyword
                                     ) {
        ModelAndView modelAndView = new ModelAndView();

        //Init parameters
        keyword = StringUtil.encodingToUtf8(keyword.trim());
        int pageSize = Pagination.initPageSize(stringPageSize.trim());
        int currentPage = Pagination.initCurrentPage(stringCurrentPage.trim());
        order = Pagination.initOrder(order.trim());

        //Deal with '%', '\', '_'
        String inputKeyword = StringUtil.dealWithSpecialCharacter(keyword);
        //calculate totalCount
        int totalCount = questionService.getQuestionQtyByKeyword(inputKeyword);

        Pagination pagination = new Pagination(totalCount, pageSize, order, inputKeyword, currentPage);
        //solve currentPage overflow
        pagination.setCurrentPage(QuestionUtil.solveOverflow(currentPage, pagination.getPageCount()));
        //List Question
        List<Question> questionList = questionService.findQuestionsPaging(pagination);

        //Deal with '%', '\', '_'
        pagination.setKeyword(keyword);
        modelAndView.addObject(Constants.PAGINATION, pagination);
        modelAndView.addObject(Constants.QUESTION_LIST, questionList);
        modelAndView.setViewName(Constants.QUESTION_LIST_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/showCreateQuestion", method = RequestMethod.GET)
    public ModelAndView showCreateQuestion() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName(Constants.QUESTION_CREATE_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/createQuestion", method = RequestMethod.POST)
    public ModelAndView createQuestion(String questionNumber, String questionName, String choiceA, String choiceB, String choiceC, String choiceD, String correctChoice) {
            ModelAndView modelAndView = new ModelAndView();
            Question question = new Question(questionNumber.trim(), questionName.trim(), choiceA.trim(), choiceB.trim(), choiceC.trim(), choiceD.trim(), correctChoice.trim());
            question.setIsAvailable(1);
            int id = 0;
            try {
                id = questionService.createQuestion(question);
            } catch (ParamterException e) {
                Map<String, String> errorMessage = e.getErrorMessage();
                modelAndView.addObject(Constants.ERROR_MESSAGE, errorMessage);
                modelAndView.setViewName(Constants.QUESTION_CREATE_JSP);
                return modelAndView;
            }
            if (id != 0) {
                this.addSession(Constants.FLASH_MESSAGE, "Create successfully");
            } else {
                this.addSession(Constants.FLASH_MESSAGE, "Create failed");
            }
            modelAndView.setView(this.getRedirectView(Constants.QUESTION_QUERY_PAGE + "?id=" + id));
            return modelAndView;
      }

    @RequestMapping(value = "/queryQuestion", method = RequestMethod.GET)
    public ModelAndView queryQuestion(@RequestParam(defaultValue = "") String id) {
        ModelAndView modelAndView =new ModelAndView();
        //Id is "" OR is not number
        boolean validateFlag = QuestionUtil.validateId(id.trim());
        if (!validateFlag) {
            this.addSession(Constants.FLASH_MESSAGE, "The question doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
            return modelAndView;
        }

        Question question = questionService.queryQuestionById(Integer.parseInt(id.trim()));
        //ID doesn't exist
        if (question == null) {
            this.addSession(Constants.FLASH_MESSAGE, "The question doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
            return modelAndView;
        }

        modelAndView.setViewName(Constants.QUESTION_QUERY_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/confirmQuestion", method = RequestMethod.GET)
    public ModelAndView confirmQuestion(@RequestParam(defaultValue = "") String confirmId) {
        ModelAndView modelAndView =new ModelAndView();
        //Id is "" OR is not number
        boolean validateFlag = QuestionUtil.validateId(confirmId.trim());
        if (!validateFlag) {
            this.addSession(Constants.FLASH_MESSAGE, "The question doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
            return modelAndView;
        }

        int id = Integer.parseInt(confirmId.trim());
        Question question = questionService.queryQuestionById(id);
        //ID doesn't exist
        if (question == null) {
            this.addSession(Constants.FLASH_MESSAGE, "The question doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
            return modelAndView;
        }
        modelAndView.addObject(Constants.QUESTION, question);
        modelAndView.setViewName(Constants.QUESTION_EDIT_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/editQuestion", method = RequestMethod.GET)
    public ModelAndView showEditQuestion() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
        return modelAndView;
    }

    @RequestMapping(value = "/editQuestion", method = RequestMethod.POST)
    public ModelAndView editQuestion(String id,String questionNumber, String questionName, String choiceA, String choiceB,String choiceC, String choiceD, String correctChoice) {
        ModelAndView modelAndView =new ModelAndView();
        int questionId = Integer.parseInt(id.trim());

        //solve not update
        Question originalQuestion = questionService.queryQuestionById(questionId);
        Question latestQuestion = new Question(questionId, questionNumber.trim(), questionName.trim(), choiceA.trim(), choiceB.trim(), choiceC.trim(), choiceD.trim(), correctChoice.trim());
        boolean isSame = originalQuestion.equals(latestQuestion);
        if (isSame) {
            this.addSession(Constants.FLASH_MESSAGE, "Please update Question!");
            modelAndView.addObject(Constants.QUESTION, originalQuestion);
            modelAndView.setViewName(Constants.QUESTION_EDIT_JSP);
            return modelAndView;
        }
        //update
        int returnId = 0;
        try {
            returnId = questionService.editQuestion(latestQuestion);
        } catch (ParamterException e) {
            Map<String, String> errorMessage = e.getErrorMessage();
            modelAndView.addObject(Constants.QUESTION, latestQuestion);
            modelAndView.addObject(Constants.ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(Constants.QUESTION_EDIT_JSP);
            return modelAndView;
        }
        if (returnId != 0) {
            this.addSession(Constants.FLASH_MESSAGE, "Update successfully");
        } else {
            this.addSession(Constants.FLASH_MESSAGE, "Update failed");
        }
        modelAndView.setView(this.getRedirectView(Constants.QUESTION_QUERY_PAGE + "?id=" + returnId));
        return modelAndView;
    }

    @RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
    public ModelAndView deleteQuestion(String[] deleteArray) {
        ModelAndView modelAndView =new ModelAndView();
        List<String> deleteList = new ArrayList<String>();
        for (String id : deleteArray) {
           deleteList.add(id);
        }
        boolean deleteSuccess = questionService.updateQuestionAvailable(deleteList);
        if (deleteSuccess) {
            this.addSession(Constants.FLASH_MESSAGE, "Delete successfully");
        } else {
            this.addSession(Constants.FLASH_MESSAGE, "Delete failed");
        }
        modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
        return modelAndView;
    }

    //Ajax
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkUserName(String questionNumber) {
        return questionService.getQuestionQtyById(questionNumber);
    }

    //Ajax
    @RequestMapping(value = "/queryQuestionCount", method = RequestMethod.POST)
    @ResponseBody
    public int queryQuestionCount() {
        return questionService.getQuestionQtyByKeyword("");
    }
}
