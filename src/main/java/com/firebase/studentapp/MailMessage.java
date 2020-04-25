package com.firebase.studentapp;

public class MailMessage {
    //declaring the Mail structure
    String Sender, Receiver, Subject, Mail;

    public MailMessage(String sender, String receiver, String subject, String mail) {
        Sender = sender;
        Receiver = receiver;
        Subject = subject;
        Mail = mail;
    }//end of STORE constructor

    public MailMessage() {
    }//end of READ constructor

    public String getSender() {
        return Sender;
    }//end of getSender

    public void setSender(String sender) {
        Sender = sender;
    }//end of setSender

    public String getReceiver() {
        return Receiver;
    }//end of getReceiver

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }//end of setReceiver

    public String getSubject() {
        return Subject;
    }//end of getSubject

    public void setSubject(String subject) {
        Subject = subject;
    }//end of setSubject

    public String getMail() {
        return Mail;
    }//end of getMail

    public void setMail(String mail) {
        Mail = mail;
    }//end of setMail
}//end of CLASS
