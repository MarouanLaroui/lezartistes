package com.lezartistes.dao.feedback;

import com.lezartistes.models.Feedback;
import com.lezartistes.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    /**
     * Create a FeedbackDAOPostgres
     * @param connection connection to the DB
     */
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
        try {
            PreparedStatement pstatement = this.connection.prepareStatement(
                    "SELECT * FROM feedback WHERE company = ?"
            );
            pstatement.setString(1,companyName);
            ResultSet resultSet = pstatement.executeQuery();

            //Return info the info back
            if(resultSet.next()) {
                return null;
                //return new Feedback();
                //return new User(resultSet.getString(2),resultSet.getString(3));
            }
            else {
                return null;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
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
