package com.augmentun.exam.model;

import java.sql.Date;

import com.augmentun.exam.util.StringUtil;

public class Question {

    private int id;
    private String questionNumber;
    private String questionName;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String correctChoice;
    private Date createdTime;
    private Date updatedTime;
    private int isAvailable;

    //constructor
    public Question() {
        super();
    }

    public Question(String questionNumber, String questionName, String choiceA, String choiceB, String choiceC, String choiceD,
            String correctChoice) {
        super();
        this.questionNumber = questionNumber;
        this.questionName = questionName;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.correctChoice = correctChoice;
    }

    public Question(int id, String questionNumber, String questionName, String choiceA, String choiceB, String choiceC,
            String choiceD, String correctChoice) {
        super();
        this.id = id;
        this.questionNumber = questionNumber;
        this.questionName = questionName;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.correctChoice = correctChoice;
    }

    //get and set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }
    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }
    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(String correctChoice) {
        this.correctChoice = correctChoice;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getquestionNumber() {
        return questionNumber;
    }

    public void setquestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = true;
        if (!(obj instanceof Question)) {
            return false;
        }
        Question anotherQuestion = (Question)obj;

        //is not null
        if (!StringUtil.validateParam(anotherQuestion.getQuestionName())) {
            return false;
        }
        if (!StringUtil.validateParam(anotherQuestion.getChoiceA())) {
            return false;
        }
        if (!StringUtil.validateParam(anotherQuestion.getChoiceB())) {
            return false;
        }
        if (!StringUtil.validateParam(anotherQuestion.getChoiceC())) {
            return false;
        }
        if (!StringUtil.validateParam(anotherQuestion.getChoiceD())) {
            return false;
        }
        if (!StringUtil.validateParam(anotherQuestion.getCorrectChoice())) {
            return false;
        }

        //equal
        if (!anotherQuestion.getQuestionName().equals(this.getQuestionName())) {
            flag = false;
        }
        if (!anotherQuestion.getChoiceA().equals(this.getChoiceA())) {
            flag = false;
        }
        if (!anotherQuestion.getChoiceB().equals(this.getChoiceB())) {
            flag = false;
        }
        if (!anotherQuestion.getChoiceC().equals(this.getChoiceC())) {
            flag = false;
        }
        if (!anotherQuestion.getChoiceD().equals(this.getChoiceD())) {
            flag = false;
        }
        if (!anotherQuestion.getCorrectChoice().equals(this.getCorrectChoice())) {
            flag = false;
        }

        return flag;
    }

}
