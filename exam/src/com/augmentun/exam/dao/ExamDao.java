package com.augmentun.exam.dao;

import java.util.List;
import java.util.Map;

import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.ExamPagination;

public interface ExamDao {

    public int createExam(Exam exam);

    public int getExamQuantity(Map<String, String> conditions);

    public List<Exam> listExam(ExamPagination pagination);

    public Exam getExamById(int examId);

    public int updateExam(Exam exam);

    public int updateAvailable(List<Integer> examIdList);

}
