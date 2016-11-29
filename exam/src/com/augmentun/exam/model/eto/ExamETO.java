package com.augmentun.exam.model.eto;

public class ExamETO {

  //Accept conditions
    private int createUserId;
    private String examName = "";
    private String description = "";
    private String effcientTime = "";
    private String StringExamDuration = "";
    private String StringExamQuantity = "";
    private String StringExamPoints = "";
    private String StringExamScore = "";
    private String StringExamCriteria = "";

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
        this.examName = examName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public String getEffcientTime() {
        return effcientTime;
    }

    public void setEffcientTime(String effcientTime) {
        this.effcientTime = effcientTime.trim();
    }

    public String getStringExamDuration() {
        return StringExamDuration;
    }

    public void setStringExamDuration(String stringExamDuration) {
        StringExamDuration = stringExamDuration.trim();
    }

    public String getStringExamQuantity() {
        return StringExamQuantity;
    }

    public void setStringExamQuantity(String stringExamQuantity) {
        StringExamQuantity = stringExamQuantity.trim();
    }

    public String getStringExamPoints() {
        return StringExamPoints;
    }

    public void setStringExamPoints(String stringExamPoints) {
        StringExamPoints = stringExamPoints.trim();
    }

    public String getStringExamScore() {
        return StringExamScore;
    }

    public void setStringExamScore(String stringExamScore) {
        StringExamScore = stringExamScore.trim();
    }

    public String getStringExamCriteria() {
        return StringExamCriteria;
    }

    public void setStringExamCriteria(String stringExamCriteria) {
        StringExamCriteria = stringExamCriteria.trim();
    }

}
