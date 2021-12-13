package com.lezartistes.dao;

import com.lezartistes.models.Report;

import java.sql.Connection;

public class ReportDAOPostgres extends ReportDAO{

    private static ReportDAOPostgres reportDAOPostgres;

    private Connection coToDB;

    private ReportDAOPostgres(Connection connection){
        this.coToDB = connection;
    }

    public static ReportDAOPostgres getInstance(Connection connection){
        if(reportDAOPostgres == null){
            reportDAOPostgres = new ReportDAOPostgres(connection);
        }
        return reportDAOPostgres;
    }

    public Report getReportById(int id){
        return null;
    }

    public Report deleteReport(int id){
        return null;
    }

}
