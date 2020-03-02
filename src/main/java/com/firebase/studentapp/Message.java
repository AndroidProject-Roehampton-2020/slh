package com.firebase.studentapp;

/*Constructor class for building the forum sturcture
 * I will split the comment made by a student in 2 sections:
 * 1) the title, where the student will leve his name
 * 2) the message itself that the student will like to send
 * The student name will be inserted automatically and the student can not avoid it.
 * NO ANONYMOUS MESSAGES IN THIS FORUM */
public class Message {
    //declaring the automatically insertion of Name and Surname of the message sender into database
    String post_title = "Student " + Session.LiveSession.user.getFn() + " " + Session.LiveSession.user.getSn() + " says: ";

    //declaring the message itself
    String message;

    //Making the constructor to STORE data into Firebase
    public Message(String post_title, String message) {
        this.post_title = post_title;
        this.message = message;
                                }//end of STORE constructor

    //Making constructor to READ data from Firebase
    public Message() { }//end of READ constructor

    //Getters and Setters
    public String getPost_title() {
        return post_title;
    }//end of getPost_title
    public void setPost_title(String post_title) { this.post_title = post_title; }//end of setPost_title
    public String getMessage() {
        return message;
    }//end of getMessage
    public void setMessage(String message) {
        this.message = message;
    }//end of setMessage

}//end of class