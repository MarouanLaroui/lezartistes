package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.ReportDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.models.Report;

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

    public Report getReportById(int id){
        return this.reportDao.getReportById(id);
    }

    public Report deleteReport(int id){
        return this.deleteReport(id);
    }
}
