package com.firebase.studentapp;

//Constructor Class for Reservations from Library
public class Reviews {
    String review;
    String review_title;

    public Reviews(String review, String review_title) {
        this.review = review;
        this.review_title = review_title;
    }

    public Reviews(String s) {
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview_title() {
        return review_title;
    }

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }
}//end of Class
