package com.augmentun.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.augmentun.exam.Constants;
import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.ExamPagination;
import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.eto.ExamETO;
import com.augmentun.exam.model.eto.ExamPaginationETO;
import com.augmentun.exam.service.ExamService;
import com.augmentun.exam.util.StringUtil;
import com.augmentun.exam.util.question.QuestionUtil;
@Controller
@RequestMapping("page/exam")
public class ExamController extends BaseController{
    @Autowired
    private ExamService examService;

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @RequestMapping(value = "/showCreateExam", method = RequestMethod.GET)
    public ModelAndView showCreateExam() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Constants.EXAM_CREATE_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/createExam", method = RequestMethod.POST)
    public ModelAndView createExam(ExamETO examETO) {
        ModelAndView modelAndView = new ModelAndView();
        examETO.setCreateUserId(this.getUserId());
        int examId = 0;
        try {
            examId = examService.createExam(examETO);
        } catch (ParamterException e) {
            modelAndView.addObject(Constants.ERROR_MESSAGE, e.getErrorMessage());
            modelAndView.setViewName(Constants.EXAM_CREATE_JSP);
            return modelAndView;
        }

        if (examId != 0) {
            this.addSession(Constants.FLASH_MESSAGE, "Create successfully");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_QUERY_PAGE + "?id=" + examId));
        } else {
            modelAndView.addObject(Constants.FLASH_MESSAGE, "Sorry, the questions are not enough in the system!");
            modelAndView.addObject(Constants.EXAMETO, examETO);
            modelAndView.setViewName(Constants.EXAM_CREATE_JSP);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/draftExam", method = RequestMethod.POST)
    public ModelAndView draftExam(ExamETO examETO){
        ModelAndView modelAndView = new ModelAndView();
        examETO.setCreateUserId(this.getUserId());
        try {
            int examId = examService.createDraftExam(examETO);
            this.addSession(Constants.FLASH_MESSAGE, "Create as a draft");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_QUERY_PAGE + "?id=" + examId));

        } catch (ParamterException e) {
            modelAndView.addObject(Constants.ERROR_MESSAGE, e.getErrorMessage());
            modelAndView.setViewName(Constants.EXAM_CREATE_JSP);
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/listExam", method = RequestMethod.GET)
    public ModelAndView listExam(ExamPaginationETO pageETO) {
        ModelAndView modelAndView = new ModelAndView();
        //Init params
        int currentPage = Pagination.initCurrentPage(pageETO.getCurrentPage());
        int pageSize = Pagination.initPageSize(pageETO.getPageSize());
        //to UTF-8
        String keyword = StringUtil.encodingToUtf8(pageETO.getKeyword());
        //Init Order
        String order = ExamPagination.initOrder(pageETO.getOrder());
        //Deal with '%', '\'
        String inputKeyword = StringUtil.dealWithSpecialCharacter(keyword);
        //init Date and solve startDate > endDate
        Map<String, String> dateMap = ExamPagination.initDate(pageETO.getStartDate(), pageETO.getEndDate());
        String startDate = dateMap.get("startDate");
        String endDate = dateMap.get("endDate");

        //calculate pageCount
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("keyword", inputKeyword);
        conditions.put("startDate", startDate);
        conditions.put("endDate", endDate);
        int pageCount = examService.getPageCount(conditions, pageSize);

        //solve currentPage overflow
        currentPage = QuestionUtil.solveOverflow(currentPage, pageCount);

        ExamPagination pagination = new ExamPagination();
        pagination.setPageCount(pageCount);
        pagination.setOrder(order);
        pagination.setKeyword(inputKeyword);
        pagination.setPageSize(pageSize);
        pagination.setCurrentPage(currentPage);
        pagination.setStartDate(startDate);
        pagination.setEndDate(endDate);
        List<Exam> examList = examService.listExam(pagination);

        //Deal with '%', '\'
        pagination.setKeyword(keyword);
        modelAndView.addObject(Constants.EXAM_LIST, examList);
        modelAndView.addObject(Constants.PAGINATION, pagination);
        modelAndView.setViewName(Constants.EXAM_LIST_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/queryExam", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView queryExam(@RequestParam (value = "id", defaultValue = "") String examId) {
        ModelAndView modelAndView = new ModelAndView();
        boolean isInt = StringUtil.parseIntSuccess(examId);
        if (!isInt) {
            this.addSession(Constants.FLASH_MESSAGE, "The exam doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE));
            return modelAndView;
        }
        int id = Integer.parseInt(examId);
        Exam exam = examService.getAvailableExam(id);
        if (exam == null) {
            this.addSession(Constants.FLASH_MESSAGE, "The exam doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE));
            return modelAndView;
        }
        modelAndView.addObject(Constants.EXAM, exam);
        modelAndView.setViewName(Constants.EXAM_QUERY_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/confirmExam", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmExam(@RequestParam (value = "id", defaultValue = "") String examId) {
        ModelAndView modelAndView = new ModelAndView();
        boolean isInt = StringUtil.parseIntSuccess(examId);
        if (!isInt) {
            this.addSession(Constants.FLASH_MESSAGE, "The exam doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE));
            return modelAndView;
        }
        int id = Integer.parseInt(examId);
        Exam exam = examService.getAvailableExam(id);

        //id doesn't exist
        if (exam == null) {
            this.addSession(Constants.FLASH_MESSAGE, "The exam doesn't exist");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE));
            return modelAndView;
        }

        //canEdit = 0 and isDraft = 0 can't edit
        int canEdit = exam.getCanBeEdit();
        int isDraft = exam.getIsDraft();
        if (canEdit == 0 && isDraft == 0) {
            this.addSession(Constants.FLASH_MESSAGE, "The exam can't be edit");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE ));
            return modelAndView;
        }
        //2016-08-01T12:00 format
        exam.setEffcientTime(exam.getEffcientTime().replace(" ", "T"));
        modelAndView.addObject(Constants.EXAM, exam);
        modelAndView.setViewName(Constants.EXAM_EDIT_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/editExam", method = RequestMethod.POST)
    public ModelAndView editExam(ExamETO examETO, String id) {
        ModelAndView modelAndView = new ModelAndView();
        //check the time and draft
        int examId = Integer.parseInt(id);
        Exam originalExam = examService.getAvailableExam(examId);
        if (originalExam.getCanBeEdit() == 0 && originalExam.getIsDraft() == 0) {
            this.addSession(Constants.FLASH_MESSAGE, "The exam can't be edit");
            modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE));
            return modelAndView;
        }

        //update exam
        examETO.setCreateUserId(this.getUserId());
        boolean editSuccess = false;
        try {
            editSuccess = examService.editExam(examETO, originalExam);
        } catch (ParamterException e) {
            modelAndView.addObject(Constants.ERROR_MESSAGE, e.getErrorMessage());
            modelAndView.addObject(Constants.EXAM, originalExam);
            modelAndView.setViewName(Constants.EXAM_EDIT_JSP);
            return modelAndView;
        }
        if (editSuccess) {
            this.addSession(Constants.FLASH_MESSAGE, "Update exam successfully");
        } else {
            this.addSession(Constants.FLASH_MESSAGE, "Update exam failed");
        }
        modelAndView.setView(this.getRedirectView(Constants.EXAM_QUERY_PAGE + "?id=" + examId));
        return modelAndView;
    }

    @RequestMapping(value = "/deleteExam", method = RequestMethod.POST)
    public ModelAndView deleteExam(String[] deleteArray) {
        ModelAndView modelAndView = new ModelAndView();
        List<Integer> examIdList = new ArrayList<Integer>();
        for (String id: deleteArray) {
            examIdList.add(Integer.parseInt(id));
        }
        boolean deleteSuccess = examService.deleteExam(examIdList);
        if (deleteSuccess) {
            this.addSession(Constants.FLASH_MESSAGE, "Delete exam successfully");
        } else {
            this.addSession(Constants.FLASH_MESSAGE, "Delete exam failed");
        }
        modelAndView.setView(this.getRedirectView(Constants.EXAM_LIST_PAGE));
        return modelAndView;

    }
}

