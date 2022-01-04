package com.lezartistes.dao;

import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.models.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private Report resultSetToReport(ResultSet rs) throws SQLException {
        Report r = new Report(
                rs.getString("title"),
                rs.getString("general_description"),
                rs.getBoolean("is_posted"),
                rs.getDate("visit_date"),
                (String) rs.getObject("inspection_team"),
                rs.getString("necessary_means"),
                rs.getString("meteo"),
                rs.getDouble("ambient_temperature"),
                rs.getString("location"),
                rs.getString("observation"),
                rs.getBytes("file1"),
                rs.getBytes("file2"),
                rs.getBytes("file3")
                );
        r.setId(rs.getInt("id"));
        return r;

    }

    public Report getReportById(int id) throws ReportNotFoundException{

        Report report = null;
        String sqlSelect = "SELECT * FROM reports WHERE id=?";

        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le rapport si trouvé, exception sinon*/
            if(rs.next()){
                report = this.resultSetToReport(rs);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*Si report vide*/
        if(report == null){
            throw new ReportNotFoundException(id);
        }
        return report;
    }


    //TODO : CHANGE IN FUNCTION OF GROUP MEMBER IMPLEMENTATION
    public List<Report> getReportByCallForProposal(int id) throws ReportNotFoundException{

        List<Report> reports = new ArrayList<>();
        String sqlSelect = "SELECT * FROM reports WHERE reports.callforproposal =?";

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie les rapport si trouvés, rempli le tableau*/
            while (rs.next()){
                reports.add(this.resultSetToReport(rs));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*If empty return not found exception*/
        if(reports.isEmpty()){
            throw new ReportNotFoundException();
        }
        return reports;

    }


    //TODO : CHANGE IN FUNCTION OF GROUP MEMBER IMPLEMENTATION
    public List<Report> getReportByServiceProvider(int id) throws ReportNotFoundException{

        List<Report> reports = new ArrayList<>();
        String sqlSelect = "SELECT * FROM reports WHERE reports.author =?";

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie les rapport si trouvés, rempli le tableau*/
            while (rs.next()){
                reports.add(this.resultSetToReport(rs));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*If empty return not found exception*/
        if(reports.isEmpty()){
            throw new ReportNotFoundException();
        }
        return reports;

    }

    @Override
    public Report createReport(Report report){

        ArrayList<byte[]> files = new ArrayList<>();
            PreparedStatement ps = null;
            try {
                ps = this.coToDB.prepareStatement("INSERT INTO reports VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1,15);
                ps.setString(2,report.getTitle());
                ps.setString(3,report.getGeneral_description());
                ps.setBoolean(4,report.isPosted());
                ps.setDate(5,new java.sql.Date(report.getVisit_date().getTime()));
                ps.setString(6,report.getInspection_team());
                ps.setString(7,report.getNecessary_means());
                ps.setString(8,report.getMeteo());
                ps.setDouble(9,report.getAmbient_temperature());
                ps.setString(10,report.getLocation());
                ps.setString(11,report.getObservation());
                ps.setBytes(12,report.getImg1());
                ps.setBytes(13,report.getImg2());
                ps.setBytes(14,report.getImg3());
                int rows = ps.executeUpdate();
                ps.close();
                System.out.println(rows);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        return report;

    }

    public List<Report> getAllReports() throws ReportNotFoundException{

        List<Report> reports = new ArrayList<>();
        String sqlSelect = "SELECT * FROM reports";

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le client si trouvé, rempli le tableau*/
            while (rs.next()){
                reports.add(this.resultSetToReport(rs));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*If empty return not found exception*/
        if(reports.isEmpty()){
            throw new ReportNotFoundException();
        }
        return reports;

    }


    //TODO : Finir
    public Report updateReport(int id,Report report) throws ReportNotFoundException{

        String sqlSelect = "UPDATE reports " +
                "SET title=?," +
                "general_description=?," +
                "is_posted=?," +
                "visit_date=?," +
                "inspection_team=?," +
                "necessary_means=?," +
                "meteo=?," +
                "ambient_temperature=?," +
                "location=?," +
                "observation=?," +
                "file1=?," +
                "file2=?" +
                "file3=?" +
                "WHERE id=?";

        PreparedStatement pstatement = null;
        try {
            pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setString(1,report.getTitle());
            pstatement.setString(2,report.getGeneral_description());
            pstatement.setBoolean(3,report.isPosted());
            pstatement.setDate(4,new java.sql.Date(report.getVisit_date().getTime()));
            pstatement.setString(5,report.getInspection_team());
            pstatement.setString(6,report.getNecessary_means());
            pstatement.setString(7,report.getMeteo());
            pstatement.setDouble(8,report.getAmbient_temperature());
            pstatement.setString(9,report.getLocation());
            pstatement.setString(10,report.getObservation());
            pstatement.setBytes(11,report.getImg1());
            pstatement.setBytes(12,report.getImg2());
            pstatement.setBytes(13,report.getImg3());
            pstatement.setInt(14,id);

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
