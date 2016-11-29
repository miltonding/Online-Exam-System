package com.augmentun.exam.util.question;

import com.augmentun.exam.model.Question;
import com.augmentun.exam.util.StringUtil;

public class QuestionUtil {

    public static Question htmlEncode(Question question) {
        if (question == null) {
            return null;
        }
        question.setquestionNumber(StringUtil.htmlEncode(question.getquestionNumber()));
        question.setQuestionName(StringUtil.htmlEncode(question.getQuestionName()));
        question.setChoiceA(StringUtil.htmlEncode(question.getChoiceA()));
        question.setChoiceB(StringUtil.htmlEncode(question.getChoiceB()));
        question.setChoiceC(StringUtil.htmlEncode(question.getChoiceC()));
        question.setChoiceD(StringUtil.htmlEncode(question.getChoiceD()));
        return question;
    }

    public static boolean validateId(String id) {
        boolean result = true;
        if (id.equals("")) {
            result = false;
        }

        try {
            Integer.parseInt(id);
        } catch (Exception e) {
            result = false;
        }
        return result;

    }

  //currentPage < 1 or currentPage > pageSize
    public static int solveOverflow(int currentPage, int pageCount) {
        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }
        return currentPage;
    }

}
