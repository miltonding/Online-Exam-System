package com.augmentun.exam.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentun.exam.dao.ExamDao;
import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.ExamPagination;

public class ExamDaoImpl extends SqlSessionDaoSupport implements ExamDao {
    private static final String CLASS_NAME = Exam.class.getName();

    @Override
    public int createExam(Exam exam) {
        getSqlSession().insert(CLASS_NAME + ".createExam", exam);
        return exam.getId();
    }

    @Override
    public List<Exam> listExam(ExamPagination pagination) {
        return getSqlSession().selectList(CLASS_NAME + ".listExam", pagination);
    }

    @Override
    public int getExamQuantity(Map<String, String> conditions) {
        return getSqlSession().selectOne(CLASS_NAME + ".getExamQuantity", conditions);
    }

    @Override
    public Exam getExamById(int examId) {
        return getSqlSession().selectOne(CLASS_NAME + ".getExamById", examId);
    }

    @Override
    public int updateExam(Exam exam) {
        return getSqlSession().update(CLASS_NAME + ".updateExam", exam);
    }

    @Override
    public int updateAvailable(List<Integer> examIdList) {
        return getSqlSession().update(CLASS_NAME + ".updateAvailable", examIdList);
    }
}
