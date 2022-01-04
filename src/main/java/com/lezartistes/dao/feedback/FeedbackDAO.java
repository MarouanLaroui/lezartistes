package com.lezartistes.dao.feedback;

import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.models.Feedback;

import java.io.Serializable;
import java.util.List;

public abstract class FeedbackDAO implements Serializable {
    public FeedbackDAO(){
        super();
    }

    public abstract List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException;
    public abstract List<Feedback> getFeedbackByCompanyByRating(String companyName, String rating);
    public abstract List<Feedback> getAllFeedbackByCompany(String companyName) throws FeedbackNotFoundException;
    public abstract int addFeedback(Feedback fb);
    public abstract int modifyFeedback(int idFeedback, Feedback fb);
    public abstract int deleteFeedback(int idFeedback);
}
