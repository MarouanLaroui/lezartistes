package com.lezartistes.dao.feedback;

import com.lezartistes.models.Client;
import com.lezartistes.models.Feedback;
import com.lezartistes.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Feedback> getFeedbackByCompanyByRating(String companyName, String rating) {
        return null;
    }

    @Override
    public List<Feedback> getAllFeedbackByCompany(String companyName) {
        /**todo: requête SQL qui récupère l'id de la company qui correspond au name passé en paramètre
         * todo: requête SQL qui récupère le feedback associé
         */
        String sqlSelect = "SELECT * FROM feedbacks";
        List<Feedback> feedbacks = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                // todo: créer feedbkck et add feedback à la liste
                //Feedback feedback = new Feedback(resultSet.getInt(2), resultSet.getString(3),resultSet.getInt(4) );
                //feedbacks.add(feedback);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return feedbacks;
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
