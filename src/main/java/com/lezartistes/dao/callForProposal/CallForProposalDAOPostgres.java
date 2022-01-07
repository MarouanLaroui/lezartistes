package com.lezartistes.dao.callForProposal;

import com.lezartistes.dao.ClientDAOPostgres;
import com.lezartistes.dao.feedback.FeedbackDAOPostgres;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Client;
import com.lezartistes.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CallForProposalDAOPostgres extends CallForProposalDAO{

    /**
     * callForProposalDAOPostgres for the Singleton Pattern
     */
    private static CallForProposalDAOPostgres callForProposalDAOPostgres;
    private final ClientDAOPostgres clientDAOPostgres;

    /**
     * Connection to the database
     */
    private Connection connection;

    /**
     * Constructor : create a CallForProposalDAOPostgres
     * @param connection
     */
    public CallForProposalDAOPostgres(Connection connection){
        super();
        this.connection = connection;
        this.clientDAOPostgres = new ClientDAOPostgres(connection);
    }

    /**
     * Method for the singleton pattern
     * @param connection connection to the DB
     * @return an only instance of CallForProposalDAOPostgres
     */
    public static CallForProposalDAO getInstance(Connection connection){
        if(callForProposalDAOPostgres == null){
            callForProposalDAOPostgres = new CallForProposalDAOPostgres(connection);
        }
        return callForProposalDAOPostgres;
    }

    /*methods*/
    public CallForProposal resultSetToCallForProposal(ResultSet rs) throws SQLException{
        CallForProposal cfp = new CallForProposal(
                rs.getString("title"),
                rs.getString("general_description"),
                rs.getBytes("imgsignature"),
                rs.getInt("report"),
                rs.getInt("author"),
                rs.getInt("building")
        );
        cfp.setId(rs.getInt("idCFP"));
        cfp.setStatus(rs.getString("status").toUpperCase().trim());
        return cfp;
    }

    @Override
    public CallForProposal getCallForProposalById(int id) {
        String sqlSelect = "SELECT * FROM callforproposals WHERE idcfp=?";

        PreparedStatement pstatement = null;

        try{
            pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet resultSet = pstatement.executeQuery();

            if(resultSet.next()){
                return this.resultSetToCallForProposal(resultSet);
            }
            else{
                throw new CallForProposalNotFoundException(id);
            }
        }
        catch(SQLException | CallForProposalNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<CallForProposal> getAllCallForProposal() {
        return null;
    }

    @Override
    public List<CallForProposal> getCallForProposalByAuthor(int id) {
        return null;
    }

    @Override
    public CallForProposal createCallForProposal(CallForProposal cfp) {
        return null;
    }

    @Override
    public CallForProposal deleteCallForProposal(CallForProposal cfp) {
        return null;
    }

    @Override
    public CallForProposal modifyCallForProposal(CallForProposal cfp) {
        return null;
    }
}
