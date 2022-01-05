package com.lezartistes.dao.feedback;

import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.models.Feedback;

import java.io.Serializable;
import java.util.List;

public abstract class FeedbackDAO implements Serializable {
    //constructor
    public FeedbackDAO(){
        super();
    }

    //methods
    public abstract Feedback getFeedbackById(int id) throws FeedbackNotFoundException;
    public abstract List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException;
    public abstract List<Feedback> getFeedbackByCompanyByRating(String companyName, String rating) throws CompanyNotFoundException;
    public abstract List<Feedback> getAllFeedbackByCompany(String companyName) throws FeedbackNotFoundException, CompanyNotFoundException;
    public abstract int addFeedback(Feedback fb);
    public abstract int modifyFeedback(int idFeedback, Feedback fb);
    public abstract int deleteFeedback(int idFeedback);
}
