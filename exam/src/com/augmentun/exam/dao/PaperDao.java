package com.augmentun.exam.dao;

import com.augmentun.exam.model.Paper;

public interface PaperDao {

    public int createPaper(Paper paper);

    public int updateQuestionIdArr(Paper paper);

    public int getPaperQuantity(int examId);

    public Paper getPaperByExamId(int examId);
}
