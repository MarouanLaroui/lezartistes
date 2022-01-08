package com.lezartistes.dao.callForProposal;

import com.lezartistes.dao.ClientDAOPostgres;
import com.lezartistes.exceptions.CFPIllegalChangeOfStateException;
import com.lezartistes.exceptions.CallForProposalDeleteImpossibleException;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Status;

import java.sql.*;
import java.util.ArrayList;
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
    public List<CallForProposal> getAllCallForProposal() throws CallForProposalNotFoundException {
        String sqlSelect = "SELECT * FROM callforproposals";
        List<CallForProposal> calls = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                CallForProposal cfp = resultSetToCallForProposal(resultSet);
                calls.add(cfp);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(calls.isEmpty()){
            throw new CallForProposalNotFoundException();
        }
        return calls;
    }

    @Override
    public List<CallForProposal> getAllPostedCallForProposal() throws CallForProposalNotFoundException {
        String sqlSelect = "SELECT * FROM callforproposals WHERE status=?";
        List<CallForProposal> calls = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            //todo: tester si ça fonctionne en upper case ou passer en lower case
            pstatement.setString(1,Status.POSTED.name());
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                CallForProposal cfp = resultSetToCallForProposal(resultSet);
                calls.add(cfp);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(calls.isEmpty()){
            throw new CallForProposalNotFoundException();
        }
        return calls;
    }

    @Override
    public List<CallForProposal> getCallForProposalByAuthor(String authorMail) throws CallForProposalNotFoundException {
        String sqlSelect = "SELECT * FROM callforproposals " +
                "JOIN clients ON callforproposals.author = clients.id_clients " +
                "WHERE clients.username=?";
        List<CallForProposal> calls = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setString(1, authorMail.trim());
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                CallForProposal cfp = resultSetToCallForProposal(resultSet);
                calls.add(cfp);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(calls.isEmpty()){
            throw new CallForProposalNotFoundException(authorMail);
        }
        return calls;
    }

    @Override
    public int updateStatusOfCallForProposal(CallForProposal cfp, String newStatus) throws CFPIllegalChangeOfStateException {
        String status = newStatus.toUpperCase().trim();
        String actualStatus = cfp.getStatus();
        int affectedRows = 0;

        switch(status){
            //si l'utilisateur veut changer le statut en DRAFT ou OVER
            case "DRAFT":
            case "OVER":
                //le cfp doit être dans l'état POSTED, sinon erreur
                if (actualStatus.equals("POSTED")){
                    affectedRows = updateStatus(cfp.getId(), status);
                }
                else{
                    throw new CFPIllegalChangeOfStateException(actualStatus, status);
                }
                break;

            //si l'utilisateur veut changer le statut en POSTED
            case "POSTED":
                //le cfp doit être en état DRAFT, sinon erreur
                if (actualStatus.equals("DRAFT")){
                    affectedRows = updateStatus(cfp.getId(), status);
                }
                else{
                    throw new CFPIllegalChangeOfStateException(actualStatus, status);
                }
                break;

            //si l'utilisateur veut changer le statut en ARCHIVED
            case "ARCHIVED":
                //le cfp doit être en état OVER, sinon erreur
                if (actualStatus.equals("OVER")){
                    affectedRows = updateStatus(cfp.getId(), status);
                }
                else{
                    throw new CFPIllegalChangeOfStateException(actualStatus, status);
                }
                break;
        }

        return affectedRows;
    }

    public int updateStatus(int idCFP, String newStatus){
        int affectRows = 0;
        String sqlInsert = "UPDATE callforproposals " +
                "SET status=? " +
                "WHERE idcfp=?";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,newStatus);
            pstmt.setInt(2,idCFP);

            affectRows = pstmt.executeUpdate();
            pstmt.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int createCallForProposal(CallForProposal cfp) {
        //TODO: Remplacer par des autoincrémentales keys
        int affectRows = 0;
        String sqlInsert = "INSERT INTO callforproposals(title, general_description, imgsignature, author, status, building) " +
                "VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,cfp.getTitle());
            pstmt.setString(2,cfp.getGeneral_description());
            pstmt.setBytes(3,cfp.getSignature());
            pstmt.setInt(4,cfp.getIdClientAuthor());
            pstmt.setString(5,cfp.getStatus());
            pstmt.setInt(6,cfp.getBuilding());

            affectRows = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int updateCallForProposal(int idCFP, CallForProposal cfp) throws CFPIllegalChangeOfStateException {
        int affectRows = 0;
        String sqlUpdate = "UPDATE callforproposals SET " +
                "title = ?, " +
                "general_description = ? , " +
                "imgsignature = ?, " +
                "author = ?, " +
                "status = ?, " +
                "building = ? " +
                "WHERE idcfp = ?";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,cfp.getTitle());
            pstmt.setString(2,cfp.getGeneral_description());
            pstmt.setBytes(3,cfp.getSignature());
            pstmt.setInt(4,cfp.getIdClientAuthor());
            pstmt.setString(5,cfp.getStatus());
            pstmt.setInt(6,cfp.getBuilding());
            pstmt.setInt(7,idCFP);

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int deleteCallForProposal(CallForProposal cfp) throws CallForProposalDeleteImpossibleException {
        int affectRows = 0;

        //todo: vérifier s'il y a des rapports associés à un CFP

        //S'il y a des rapports associés à des call for proposal, la suppression est impossible
        String sqlDelete = "DELETE FROM callforproposals WHERE idcfp=?";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, cfp.getId());

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }
}
