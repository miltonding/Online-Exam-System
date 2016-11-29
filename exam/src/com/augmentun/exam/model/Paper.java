package com.augmentun.exam.model;

public class Paper {

    private int id;
    private int examId;
    private String questionIdArray;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getQuestionIdArray() {
        return questionIdArray;
    }

    public void setQuestionIdArray(String questionIdArray) {
        this.questionIdArray = questionIdArray;
    }

}
