package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.ReportDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.models.Report;

import java.io.FileNotFoundException;
import java.util.List;

public class ReportFacade {

    /*attributes*/
    private static ReportFacade reportFacade;
    private ReportDAO reportDao;

    /*constructor*/
    private ReportFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.reportDao = factory.createReportDAO();
    }

    /*methods*/
    public static ReportFacade getInstance(){
        if(reportFacade == null){
            reportFacade = new ReportFacade();
        }
        return reportFacade;
    }

    public Report getReportById(int id) throws ReportNotFoundException {
        return this.reportDao.getReportById(id);
    }

    public List<Report> getAllReports() throws ReportNotFoundException{
        return this.reportDao.getAllReports();
    }

    public List<Report> getReportsByAuthor(int id) throws ReportNotFoundException{
        //TODO : CREATE THE REAL FUNCTION
        return this.reportDao.getAllReports();
    }

    public List<Report> getReportsForProposal(int callForProposalId) throws ReportNotFoundException{
        //TODO : CREATE THE REAL FUNCTION
        return this.reportDao.getAllReports();
    }

    public Report createReport(Report report) throws FileNotFoundException {
        return this.reportDao.createReport(report);
    }

    public Report updateReport(Report report) throws ReportNotFoundException {
        return this.reportDao.updateReport(report);
    }

    public Report deleteReport(int id) throws ReportNotFoundException {
        return this.reportDao.deleteReport(id);
    }
}
