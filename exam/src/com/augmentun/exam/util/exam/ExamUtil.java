package com.augmentun.exam.util.exam;

import java.util.ArrayList;
import java.util.List;

import com.augmentun.exam.model.Exam;
import com.augmentun.exam.util.DateUtil;
import com.augmentun.exam.util.StringUtil;
public class ExamUtil {

    public static Exam onehtmlEncode(Exam exam) {
        if (exam == null) {
            return null;
        }
        String examName = StringUtil.htmlEncode(exam.getExamName());
        String description = StringUtil.htmlEncode(exam.getDescription());
        String creatorName = StringUtil.htmlEncode(exam.getCreatorName());
        if (exam.getIsDraft() == 1) {
            exam.setShowId(exam.getId() + "(*)");
        } else {
            exam.setShowId(exam.getId() + "");
        }
        //whether can edit
        if (canBeEdit(exam)) {
            exam.setCanBeEdit(1);
        } else {
            exam.setCanBeEdit(0);
        }
        exam.setEffcientTime(DateUtil.dateToListString(exam.getEffcientDate()));
        exam.setExamName(examName);
        exam.setDescription(description);
        exam.setCreatorName(creatorName);
        return exam;
    }

    public static List<Exam> listHtmlEncode(List<Exam> examList) {
        List<Exam> htmlEncodeList = new ArrayList<Exam>();
        if (examList == null || examList.size() == 0) {
            return null;
        }
        for (Exam exam: examList) {
            Exam htmlExam = onehtmlEncode(exam);
            htmlEncodeList.add(htmlExam);
        }
        return htmlEncodeList;
    }

    public static boolean canBeEdit(Exam exam) {
        if (exam == null || exam.getEffcientDate() == null) {
            return false;
        }
        return DateUtil.isAfterNow(exam.getEffcientDate());
    }

}

