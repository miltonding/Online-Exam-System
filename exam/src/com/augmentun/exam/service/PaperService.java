package com.augmentun.exam.service;

import com.augmentun.exam.model.Paper;


public interface PaperService {

    public void createPaper(int questionQuantity, int examId);

    public Paper getPaperByExamId(int examId);

}
