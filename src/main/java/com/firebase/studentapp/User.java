
package com.firebase.studentapp;
public class User {
    /*Declaring initial objects to be used inside this class
    fn=First Name;
    sn= Surname;
    id= Student ID;
    email=Student Email;
    password= Student Login Password;
    */
    String fn, sn, id,email,password;
    //Making the constructor to STORE data on the Firebase Database under the "_user_" root
    public User(String fn, String sn, String id, String email, String password) {
        this.fn = fn;
        this.sn = sn;
        this.id = id;
        this.email = email;
        this.password = password;
    }
    //Making the constructor to READ data on the Firebase Database under the "_user_" root
    public User() {
    }
    // Getters and Setters
    public String getFn() {
        return fn;
    } // end of getFN
    public void setFn(String fn) {
        this.fn = fn;
    } //end of setFN
    public String getSn() {
        return sn;
    }//end of getSN
    public void setSn(String sn) {
        this.sn = sn;
    }//end of setSN
    public String getId() {
        return id;
    } //end of getId
    public void setId(String id) {
        this.id = id;
    }//end of setId
    public String getEmail() {
        return email;
    }//end of getEmail
    public void setEmail(String email) {
        this.email = email;
    }//end of setEmail
    public String getPassword() {
        return password;
    }//end of getPassword
    public void setPassword(String password) {
        this.password = password;
    }//end of setPassword
} //end of Class
