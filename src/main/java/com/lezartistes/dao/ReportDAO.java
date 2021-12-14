package com.lezartistes.dao;

import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.models.Report;

import java.io.Serializable;

public abstract class ReportDAO implements Serializable {

    public ReportDAO(){ super(); }

    public abstract Report getReportById(int id) throws ReportNotFoundException;

    public abstract Report deleteReport(int id) throws ReportNotFoundException;
}
