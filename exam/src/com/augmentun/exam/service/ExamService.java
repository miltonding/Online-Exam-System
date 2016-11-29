package com.augmentun.exam.service;

import java.util.List;
import java.util.Map;

import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.ExamPagination;
import com.augmentun.exam.model.eto.ExamETO;

public interface ExamService {

    public int createExam(ExamETO examETO) throws ParamterException ;

    public int createDraftExam(ExamETO examETO) throws ParamterException;

    public int getPageCount(Map<String, String> conditions, int pageSize);

    public List<Exam> listExam(ExamPagination pagination);

    public Exam getAvailableExam(int examId);

    public boolean editExam(ExamETO examETO,Exam originalExam) throws ParamterException;

    public boolean deleteExam(List<Integer> examIdList);
}
