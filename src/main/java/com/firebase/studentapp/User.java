package com.firebase.studentapp;

public class User {

    String fn, sn, id;

    //USed when we save to FB


    public User(String fn, String sn, String id) {
        this.fn = fn;
        this.sn = sn;
        this.id = id;
    }

    //Used when we Read from FB
    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
