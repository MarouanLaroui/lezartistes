package com.lezartistes.models;

public class Feedback {
    private int rating;
    private String comment;
    private Company company;

    /**
     * Constructor
     * @param rating rating given to a specific expert
     * @param comment desciption to explain the rating and the feedback
     * @param company the expert related to this rating
     */
    public Feedback(int rating, String comment, Company company){
        this.rating = rating;
        this.comment = comment;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
