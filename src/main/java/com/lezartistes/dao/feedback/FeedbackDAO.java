package com.lezartistes.dao.feedback;

import com.lezartistes.models.Feedback;

import java.io.Serializable;
import java.util.List;

public abstract class FeedbackDAO implements Serializable {
    public FeedbackDAO(){
        super();
    }

    public abstract List<Feedback> getAllFeedbacks();
    public abstract List<Feedback> getFeedbackByCompanyByRating(String companyName, String rating);
    public abstract List<Feedback> getAllFeedbackByCompany(String companyName);
    public abstract Feedback addFeedback(String companyName, String client, String comment, int rate);
    public abstract Feedback modifyFeedback(int idFeedback, String client, String comment, int rate);
    public abstract void deleteFeedback(int idFeedback);
}
