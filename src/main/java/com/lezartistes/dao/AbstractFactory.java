package com.lezartistes.dao;

import com.lezartistes.dao.quotation.QuotationDAO;

public interface AbstractFactory {

    /**
     * Create and return a UserDAO
     * @return a UserDAO
     */
    UserDAO createUserDAO();

    ClientDAO createClientDAO();

     ReportDAO createReportDAO();

     QuotationDAO createQuotationDAO();

}
