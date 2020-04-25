package com.firebase.studentapp;

/*Constructor Class for Reservations from Library
 This class is build as same as the Message,
  but with small variable name changes and
  with a different FIrebase root*/

public class Reviews {
    /*Declaring initial objects to be used inside this class
     */
    String review;
    String review_title;

    //Making the constructor to STORE data into Firebase
    public Reviews(String review, String review_title) {
        this.review = review;
        this.review_title = review_title;
    }//end of constructor

    //Making the constructor to READ data into Firebase
    public Reviews(String s) {
    }

    //Getters and Setters
    public String getReview() {
        return review;
    }//end of getReview

    public void setReview(String review) {
        this.review = review;
    }//end of setReview

    public String getReview_title() {
        return review_title;
    } //end of getReview_title

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }//end of setReview_title
}//end of Class
