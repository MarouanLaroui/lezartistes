package com.lezartistes.dao;

import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.models.Report;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

public abstract class ReportDAO implements Serializable {

    public ReportDAO(){ super(); }

    public abstract List<Report> getAllReports() throws  ReportNotFoundException;

    public abstract Report getReportById(int id) throws ReportNotFoundException;

    public abstract Report createReport(Report report) throws FileNotFoundException;

    public abstract Report deleteReport(int id) throws ReportNotFoundException;
}
