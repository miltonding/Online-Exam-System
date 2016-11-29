package com.augmentun.exam.util.question;

import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Question;
import com.augmentun.exam.util.StringUtil;

public class ParamExceptionAddError {

    public static void getExceptionWithError(Question question, ParamterException paramterException) {
        if (!StringUtil.validateParam(question.getquestionNumber())) {
            paramterException.addErrorField("TIP_QUESTION_NUMBER", "Question ID is required");
        } else if (question.getquestionNumber().trim().length() > 10){
            paramterException.addErrorField("TIP_QUESTION_NUMBER", "Question ID is over 10");
        }

        if (!StringUtil.validateParam(question.getQuestionName())) {
            paramterException.addErrorField("TIP_QUESTION_NAME", "Question is required ");
        }else if (question.getQuestionName().trim().length() > 200) {
            paramterException.addErrorField("TIP_QUESTION_NAME", "Question is over 200 ");
        }

        if (!StringUtil.validateParam(question.getChoiceA())) {
            paramterException.addErrorField("TIP_CHOICE_A", "choiceA is required");
        }else if (question.getChoiceA().trim().length() > 200) {
            paramterException.addErrorField("TIP_CHOICE_A", "choiceA is over 200");
        }

        if (!StringUtil.validateParam(question.getChoiceB())) {
            paramterException.addErrorField("TIP_CHOICE_B", "choiceB is required");
        } else if (question.getChoiceB().trim().length() > 200) {
            paramterException.addErrorField("TIP_CHOICE_B", "choiceB is over 200");
        }

        if (!StringUtil.validateParam(question.getChoiceC())) {
            paramterException.addErrorField("TIP_CHOICE_C", "choiceB is required");
        } else if (question.getChoiceC().trim().length() > 200) {
            paramterException.addErrorField("TIP_CHOICE_C", "choiceB is over 200");
        }

        if (!StringUtil.validateParam(question.getChoiceD())) {
            paramterException.addErrorField("TIP_CHOICE_D", "choiceB is required");
        } else if (question.getChoiceD().trim().length() > 200) {
            paramterException.addErrorField("TIP_CHOICE_D", "choiceB is over 200");
        }

        if (!StringUtil.validateParam(question.getCorrectChoice())) {
            paramterException.addErrorField("TIP_CORRECT_CHOICE", "Answer is required ");
        }
    }
}
