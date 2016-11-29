package com.augmentun.exam;

public class Constants {
    //reg
    public static final String REG_EFFECTIVE_DATE = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}T[0-9]{1,2}:[0-9]{1,2}";
    public static final String REG_EFFECTIVE_YEAR = "\\d{4}-\\d{2}-\\d{2}";
    //appContext
    public static final String APP_CONTEXT_PATH = "APP_CONTEXT_PATH";
    public static final String APP_HTTP_SESSION = "APP_HTTP_SESSION";

    //JSP
    //User
    public static final String USER_LOGIN_JSP = "user/login";

    //Question
    public static final String QUESTION_LIST_JSP = "question/question_list";
    public static final String QUESTION_QUERY_JSP = "question/question_query";
    public static final String QUESTION_CREATE_JSP = "question/question_create";
    public static final String QUESTION_EDIT_JSP = "question/question_edit";

    //Exam
    public static final String EXAM_CREATE_JSP = "exam/exam_create";
    public static final String EXAM_LIST_JSP = "exam/exam_list";
    public static final String EXAM_EDIT_JSP = "exam/exam_edit";
    public static final String EXAM_QUERY_JSP = "exam/exam_query";

    //Paper
    public static final String PAPER_QUERY_JSP = "exam/paper_query";

    //Page
    public static final String USER_LOGIN_PAGE = "page/user/showLogin";
    public static final String QUESTION_LIST_PAGE = "page/question/questionList";
    public static final String QUESTION_QUERY_PAGE = "page/question/queryQuestion";
    public static final String EXAM_LIST_PAGE = "page/exam/listExam";
    public static final String EXAM_QUERY_PAGE = "page/exam/queryExam";

    //parameters
    public static final String TIP_MESSAGE = "TIP_MESSAGE";
    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    public static final String USER = "USER";
    public static final String LANGUAGE = "LANGUAGE";
    public static final String QUESTION_LIST = "QUESTION_LIST";
    public static final String PAGINATION = "PAGINATION";
    public static final String QUESTION = "QUESTION";
    public static final String FLASH_MESSAGE = "FLASH_MESSAGE";

    //exam
    public static final String EXAM = "EXAM";
    public static final String EXAMETO = "EXAMETO";
    public static final String EXAM_LIST = "EXAM_LIST";

    //paper
    public static final String PAPER = "PAPER";

    public static final String DATE_FOMAT_WHOLE = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FOMAT_LONG = "yyyy-MM-dd HH:mm";
    public static final String DATE_FOMAT_PART = "yyyy-MM-dd";

}
