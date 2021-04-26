package com.example.coba_group4.profile_page;


public class Profile 
{
    String fname;
    String mname;
    String lname;
    String email;
    String username;
    String profession;
    String idnum;

    public Profile() {};
    public Profile(String fname, String mname, String lname, String email, String username, String profession, String idnum) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.profession = profession;
        this.idnum = idnum;
    }
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIdnum() { return idnum; }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
}
