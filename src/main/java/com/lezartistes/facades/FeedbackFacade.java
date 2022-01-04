package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.feedback.FeedbackDAO;
import com.lezartistes.database.PostgresFactory;
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

    public List<Feedback> getAllFeedbacks() {
        return this.feedbackDAO.getAllFeedbacks();
    }

    public List<Feedback> getAllFeedbackByCompany(String companyName){
        //trouver le company id à partir du name
        //trouver tous les feedbacks associés à cette company
        return null;
    }
}
