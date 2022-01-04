package com.lezartistes.dao.feedback;

import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.models.Feedback;

import java.sql.*;
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

    /*methods*/
    public Feedback resultSetToFeedback(ResultSet rs) throws SQLException{
        return new Feedback(
                rs.getInt("rating"),
                rs.getString("comment"),
                rs.getInt("companyfeedback")
        );
    }

    public int getCompanyIdByName(String companyName){
        String sqlCompany = "SELECT idcompany FROM companies WHERE companyname=?";
        int idCompany = -1;

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlCompany);
            pstatement.setString(1, companyName);
            ResultSet resultSet = pstatement.executeQuery();

            if(resultSet.next())
                idCompany = resultSet.getInt("idcompany");
            else
                throw new CompanyNotFoundException(companyName);
        }
        catch (SQLException | CompanyNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return idCompany;
    }

    @Override
    public List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException {
        String sqlSelect = "SELECT * FROM feedbacks";
        List<Feedback> feedbacks = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                Feedback fb = resultSetToFeedback(resultSet);
                feedbacks.add(fb);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(feedbacks.isEmpty()){
            throw new FeedbackNotFoundException();
        }
        return feedbacks;
    }

    @Override
    public List<Feedback> getFeedbackByCompanyByRating(String companyName, String rating) {
        /**
         * Récupération idCompany
         */
        int idCompany = getCompanyIdByName(companyName);

        String sqlSelect = "SELECT * FROM feedbacks WHERE companyfeedback=? AND rating=?";
        List<Feedback> feedbacks = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setInt(1, idCompany);
            pstatement.setString(2, rating);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                Feedback fb = resultSetToFeedback(resultSet);
                feedbacks.add(fb);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return feedbacks;
    }

    @Override
    public List<Feedback> getAllFeedbackByCompany(String companyName) throws FeedbackNotFoundException {
        /**
         * On récupère la bonne company associé au nom passé en paramètre
         */
        int idCompany = getCompanyIdByName(companyName);


        /**
         * Récupération des feedbacks associés à la company
         */
        String sqlSelect = "SELECT * FROM feedbacks WHERE companyfeedback=?";
        List<Feedback> feedbacks = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setInt(1, idCompany);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                Feedback fb = resultSetToFeedback(resultSet);
                feedbacks.add(fb);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(feedbacks.isEmpty()){
            throw new FeedbackNotFoundException();
        }
        return feedbacks;
    }

    @Override
    public int addFeedback(Feedback fb) {
       //TODO: Remplacer par des autoincrémentales keys
        int affectRows = 0;
        String sqlInsert = "INSERT INTO feedbacks(rating, comment, companyfeedback) VALUES (?,?,?)";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, fb.getRating());
            pstmt.setString(2, fb.getComment());
            pstmt.setInt(3, fb.getCompany());

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int modifyFeedback(int idFeedback, Feedback fb) {
        int affectRows = 0;
        String sqlUpdate = "UPDATE feedbacks SET rating = fb.getRating(), " +
                "comment = fb.getComment(), " +
                "companyFeedback = fb.getCompany() " +
                "WHERE idfeedback = ?";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idFeedback);

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int deleteFeedback(int idFeedback) {
        int affectRows = 0;
        String sqlDelete = "DELETE FROM feedback WHERE idfeedback=?";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idFeedback);

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }
}
