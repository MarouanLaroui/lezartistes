package com.lezartistes.dao.feedback;

import com.lezartistes.models.Feedback;

import java.sql.Connection;
import java.util.List;

public class FeedbackDAOPostgres extends FeedbackDAO{

    /**
     * feedbackDaoPostgres for the Singleton Pattern
     */
    private static FeedbackDAOPostgres feedbackDaoPostgres;

    /**
     * Connection to the database
     */
    Connection connection;

    public FeedbackDAOPostgres(Connection connection){
        super();
        this.connection = connection;
    }

    public static FeedbackDAO getInstance(Connection connection) {
        if(feedbackDaoPostgres == null){
            feedbackDaoPostgres = new FeedbackDAOPostgres(connection);
        }
        return feedbackDaoPostgres;
    }

    @Override
    public Feedback getFeedbackByCompany(String companyName) {
        return null;
    }

    @Override
    public List<Feedback> getAllFeedbackByCompany(String companyName) {
        return null;
    }

    @Override
    public Feedback addFeedback(String companyName, String client, String comment, int rate) {
        return null;
    }

    @Override
    public Feedback modifyFeedback(int idFeedback, String client, String comment, int rate) {
        return null;
    }

    @Override
    public void deleteFeedback(int idFeedback) {

    }
}
