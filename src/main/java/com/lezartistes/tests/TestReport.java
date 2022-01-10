package com.lezartistes.tests;

import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.facades.ReportFacade;
import com.lezartistes.models.Report;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.util.Date;


public class TestReport {

    @Test
    public void add() throws FileNotFoundException, ReportNotFoundException {
        ReportFacade reportFacade = ReportFacade.getInstance();

        Report r = new Report("t", "Test",false, new Date(2020,8,2),"team","n","n",12,"2","obs",null,null,null);
        reportFacade.createReport(r);

        Assertions.assertTrue(reportFacade.getAllReports().remove(r));
        reportFacade.deleteReport(r.getId());

    }

}
