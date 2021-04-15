package com.example.coba_group4.occurence;

import java.util.Date;

public class Occurrence {
    String address;
    String type;
    Date submittedTime;
    String description;

    public Occurrence(String address, String type, Date submittedTime, String description) {
        this.address = address;
        this.type = type;
        this.submittedTime = submittedTime;
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(Date submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

