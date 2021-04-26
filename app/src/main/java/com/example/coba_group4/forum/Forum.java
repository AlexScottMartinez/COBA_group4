package com.example.coba_group4.forum;


import java.util.Date;

public class Forum {
    String Title;
    String Description;
    String User;
    Date Time;

    public Forum() {};
    public Forum(String title, String description, String user, Date submittedTime) {
        this.Title  = title;
        this.Description = description;
        this.User = user;
        this.Time = submittedTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String address) {
        this.Title = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String type) {
        this.Description = type;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date submittedTime) {
        this.Time = submittedTime;
    }
    public void setUser(String type) {
        this.Description = type;
    }

    public String getUser() {
        return User;
    }
}
