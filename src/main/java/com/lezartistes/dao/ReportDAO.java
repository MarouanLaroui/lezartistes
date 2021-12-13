package com.lezartistes.dao;

import com.lezartistes.models.Report;

import java.io.Serializable;

public abstract class ReportDAO implements Serializable {

    public ReportDAO(){ super(); }

    public abstract Report getReportById(int id);

    public abstract Report deleteReport(int id);
}
