package com.example.coba_group4.occurence;

import java.util.Date;

public class Occurrence {
    String address;
    String city;
    String state;
    int zipCode;
    String type;
    Date submittedTime;
    String description;

    public Occurrence(String address, String city, String state, int zipCode, String type, Date submittedTime, String description) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }
}
