package com.augmentun.exam.model;

import java.util.Date;



public class Exam {
    //status
    private int isDraft;
    private int canBeEdit;

    private int id;
    private String showId;
    //Accept conditions
    private int createUserId;
    private String examName;
    private String description;
    private String effcientTime;

    private String creatorName;
    private int examDuration;
    private double totalScore;
    private double passCriteria;
    private double singleQuestionScore;
    private int quantity;
    private Date createdTime;
    private Date effcientDate;
    private Date endTime;

    //deal with not update exam
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Exam)) {
            return false;
        }
        Exam exam = (Exam)obj;
        boolean same = true;
        //compare
        if (!exam.getExamName().equals(this.examName)) {
            same = false;
        }
        if (!exam.getDescription().equals(this.description)) {
            same = false;
        }
        if (exam.getExamDuration() != this.examDuration) {
            same = false;
        }
        if (exam.getQuantity() != this.quantity) {
            same = false;
        }
        if (exam.getSingleQuestionScore() != this.singleQuestionScore) {
            same = false;
        }
        if (exam.getTotalScore() != this.totalScore) {
            same = false;
        }
        if (exam.getPassCriteria() != this.passCriteria) {
            same = false;
        }
        return same;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(int isDraft) {
        this.isDraft = isDraft;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffcientTime() {
        return effcientTime;
    }

    public void setEffcientTime(String effcientTime) {
        this.effcientTime = effcientTime;
    }

    public int getExamDuration() {
        return examDuration;
    }
    public void setExamDuration(int examDuration) {
        this.examDuration = examDuration;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getPassCriteria() {
        return passCriteria;
    }

    public void setPassCriteria(double passCriteria) {
        this.passCriteria = passCriteria;
    }

    public double getSingleQuestionScore() {
        return singleQuestionScore;
    }

    public void setSingleQuestionScore(double singleQuestionScore) {
        this.singleQuestionScore = singleQuestionScore;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getEffcientDate() {
        return effcientDate;
    }

    public void setEffcientDate(Date effcientDate) {
        this.effcientDate = effcientDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public int getCanBeEdit() {
        return canBeEdit;
    }

    public void setCanBeEdit(int canBeEdit) {
        this.canBeEdit = canBeEdit;
    }

}
