package com.augmentun.exam.model;

import java.util.Date;

public class User {

    private int id;
    private String userName;
    private String password;
    private String picture;
    private String chieseName;
    private String gender;
    private String telNumb;
    private String email;
    private String address;
    private String createdTime;
    private String updatedTime;;
    private Date lastLoginTime;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public User(int id, String userName, String picture, String chieseName, String gender, String telNumb,
            String email, String address, String createdTime, String updatedTime, Date lastLoginTime) {
        super();
        this.id = id;
        this.userName = userName;
        this.picture = picture;
        this.chieseName = chieseName;
        this.gender = gender;
        this.telNumb = telNumb;
        this.email = email;
        this.address = address;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.lastLoginTime = lastLoginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getChieseName() {
        return chieseName;
    }

    public void setChieseName(String chieseName) {
        this.chieseName = chieseName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNumb() {
        return telNumb;
    }

    public void setTelNumb(String telNumb) {
        this.telNumb = telNumb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
