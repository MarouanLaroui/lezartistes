package com.lezartistes.models;

public class Feedback {
    private int rating;
    private String comment;
    private int company;

    /**
     * Constructor
     * @param rating rating given to a specific expert
     * @param comment desciption to explain the rating and the feedback
     * @param company the expert related to this rating
     */
    public Feedback(int rating, String comment, int company){
        this.rating = rating;
        this.comment = comment;
        this.company = company;
    }

    /*toString*/
    public String toString(){
        return "Rating : "+ this.getRating() + "/5 -" + " \" " + this.getComment() + " \" ";
    }
    /**
     * Getter and setter
     */
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }
}
