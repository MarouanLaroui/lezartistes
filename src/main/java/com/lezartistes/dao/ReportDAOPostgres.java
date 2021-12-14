package com.lezartistes.dao;

import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.models.Client;
import com.lezartistes.models.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAOPostgres extends ReportDAO{

    /*attributes*/
    private static ReportDAOPostgres reportDAOPostgres;
    private Connection coToDB;

    /*constructor*/
    private ReportDAOPostgres(Connection connection){
        this.coToDB = connection;
    }

    /*methods*/
    public static ReportDAOPostgres getInstance(Connection connection){
        if(reportDAOPostgres == null){
            reportDAOPostgres = new ReportDAOPostgres(connection);
        }
        return reportDAOPostgres;
    }

    private Report resultSetToClient(ResultSet rs) throws SQLException {
        Report r = new Report(
                rs.getString("title"),
                rs.getString("general_description"),
                rs.getBoolean("isPosted"),
                rs.getDate("visit_date"),
                (String[]) rs.getObject("inspection_team"),
                rs.getString("necessary_means"),
                rs.getString("meteo"),
                rs.getDouble("ambient_temperature"));
        return r;
    }


    public Report getReportById(int id) throws ReportNotFoundException{
        String sqlSelect = "SELECT * FROM reports WHERE id=?";
        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery(sqlSelect);

            /*Renvoie le client si trouvé, exception sinon*/
            if(rs.next()){
                return this.resultSetToClient(rs);
            }
            else{
                throw new ReportNotFoundException(id);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    //TODO : Finir
    public Report deleteReport(int id) throws ReportNotFoundException{

        String sqlSelect = "DELETE FROM reports WHERE id=?";
        PreparedStatement pstatement = null;
        try {
            pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            int affectedRows = pstatement.executeUpdate(sqlSelect);
            if(affectedRows == 0){
                throw new ReportNotFoundException(id);
            }
            return null;

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}
