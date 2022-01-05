package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.feedback.FeedbackDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.models.Feedback;

import java.util.List;

public class FeedbackFacade {
    /*attributes*/
    private static FeedbackFacade feedbackFacade;
    private final FeedbackDAO feedbackDAO;

    /*constructeur*/
    private FeedbackFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.feedbackDAO = factory.createFeedbackDAO();
    }

    /*methods*/
    public static FeedbackFacade getInstance(){
        if(feedbackFacade == null){
            feedbackFacade = new FeedbackFacade();
        }
        return feedbackFacade;
    }

    public Feedback getFeedbakById(int id) throws FeedbackNotFoundException {
        return this.feedbackDAO.getFeedbackById(id);
    }

    public List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException {
        return this.feedbackDAO.getAllFeedbacks();
    }

    public List<Feedback> getAllFeedbackByCompany(String companyName) throws FeedbackNotFoundException, CompanyNotFoundException {
        return this.feedbackDAO.getAllFeedbackByCompany(companyName);
    }

    public List<Feedback> getFeedbackByCompanyByRating(String companyName, String rating) throws CompanyNotFoundException {
        return this.feedbackDAO.getFeedbackByCompanyByRating(companyName, rating);
    }

    public int addFeedback(Feedback fb){
        return this.feedbackDAO.addFeedback(fb);
    }

    public int modifyFeedback(int idFeedback, Feedback fb){
        return this.feedbackDAO.modifyFeedback(idFeedback, fb);
    }

    public int deleteFeedback(int idFeedback){
        return this.feedbackDAO.deleteFeedback(idFeedback);
    }
}
