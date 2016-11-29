package com.augmentun.exam.util.exam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.augmentun.exam.Constants;
import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.eto.ExamETO;
import com.augmentun.exam.util.DateUtil;

public class ParamExceptionAddError {

    public static Map<String, Object> getExceptionWithError(ExamETO examETO, ParamterException paramterException) {
        Map<String, Object> map = new HashMap<String, Object>();
        Exam returnExam = new Exam();
        //create User
        int createUserId = examETO.getCreateUserId();
        if (createUserId == 0) {
            paramterException.addErrorField("tip_create_user_id", "create user id is required");
        } else {
            returnExam.setCreateUserId(createUserId);
        }

        //examName
        String examName = examETO.getExamName();
        if (examName.length() == 0) {
            paramterException.addErrorField("tip_exam_name", "exam name is required");
        } else if (examName.length() > 20) {
            paramterException.addErrorField("tip_exam_name", "length is over 20");
        }
        returnExam.setExamName(examName);

        //description
        String description = examETO.getDescription();
        if (description.length() == 0) {
            paramterException.addErrorField("tip_exam_description", "description is required");
        } else if (examName.length() > 100) {
            paramterException.addErrorField("tip_exam_description", "length is over 100");
        }
        returnExam.setDescription(description);

        //effcientTime
        String effcientTime = examETO.getEffcientTime();
        if (!effcientTime.matches(Constants.REG_EFFECTIVE_DATE)) {
            paramterException.addErrorField("tip_exam_time", "effective time is required");
        } else {
            String[] split = effcientTime.split("T");
            effcientTime = split[0] + " " + split[1];
            Date date = DateUtil.stringToDate(effcientTime , Constants.DATE_FOMAT_LONG);
            if (DateUtil.isAfterNow(date)) {
                returnExam.setEffcientTime(effcientTime);
            } else {
                paramterException.addErrorField("tip_exam_time", "effective time is invalid");
            }

        }

        //examDuration
        int examDuration = 0;
        String stringExamDuration = examETO.getStringExamDuration();
        if (stringExamDuration.length() == 0) {
            paramterException.addErrorField("tip_exam_duration", "duration is required");
        }
        try {
            examDuration = Integer.parseInt((stringExamDuration));
            returnExam.setExamDuration(examDuration);
        } catch (Exception e) {
            paramterException.addErrorField("tip_exam_duration", "duration is required");
        }

        //examQuantity
        String stringExamQuantity = examETO.getStringExamQuantity();
        int examQuantity = 0;
        if (stringExamQuantity.length() == 0) {
            paramterException.addErrorField("tip_exam_quantity", "quantity is required");
        } else if (stringExamQuantity.length() > 10) {
            paramterException.addErrorField("tip_exam_quantity", "length is over 10");
        }
        try {
            examQuantity = Integer.parseInt(stringExamQuantity);
            returnExam.setQuantity(examQuantity);
        } catch (Exception e) {
            paramterException.addErrorField("tip_exam_quantity", "quantity is required");
        }

        //examPoints
        String stringExamPoints = examETO.getStringExamPoints();
        double examPoints = 0;
        if (stringExamPoints.length() == 0) {
            paramterException.addErrorField("tip_exam_points", "points is required");
        } else if (stringExamPoints.length() > 5) {
            paramterException.addErrorField("tip_exam_points", "length is over 5");
        }
        try {
            examPoints = Double.valueOf((stringExamPoints));
            returnExam.setSingleQuestionScore(examPoints);
        } catch (Exception e) {
            paramterException.addErrorField("tip_exam_points", "points is required");
        }

        //examScore
        String stringExamScore = examETO.getStringExamScore();
        double examScore = 0;
        if (stringExamScore.length() == 0) {
            paramterException.addErrorField("tip_total_score", "total score is required");
        } else if (stringExamScore.length() > 6) {
            paramterException.addErrorField("tip_total_score", "length is over 6");
        }
        try {
            examScore = Double.valueOf((stringExamScore));
            if (examScore == examPoints * examQuantity) {
                returnExam.setTotalScore(examScore);
            } else {
                paramterException.addErrorField("tip_total_score", "total score is incorrect");
            }
        } catch (Exception e) {
            paramterException.addErrorField("tip_total_score", "total score is required");
        }

        //ExamCriteria
        String stringExamCriteria = examETO.getStringExamCriteria();
        double examCriteria = 0;
        if (stringExamCriteria.length() == 0) {
            paramterException.addErrorField("tip_exam_criteria", "total score is required");
        } else if (stringExamCriteria.length() > 6) {
            paramterException.addErrorField("tip_exam_criteria", "length is over 6");
        }
        try {
            examCriteria = Double.valueOf((stringExamCriteria));
            if (examCriteria < examScore) {
                returnExam.setPassCriteria(examCriteria);
            } else {
                paramterException.addErrorField("tip_exam_criteria", "passCriteria is over total socre");
            }

        } catch (Exception e) {
            paramterException.addErrorField("tip_exam_criteria", "total score is required");
        }

        map.put("exam", returnExam);
        map.put("exception", paramterException);
        return map;
    }
}
