package com.lezartistes.dao;

import com.lezartistes.dao.quotation.QuotationDAO;
import com.lezartistes.dao.serviceProvider.ServiceProviderDAO;

public interface AbstractFactory {

    /**
     * Create and return a UserDAO
     * @return a UserDAO
     */
    UserDAO createUserDAO();

    ClientDAO createClientDAO();

    ReportDAO createReportDAO();

     QuotationDAO createQuotationDAO();

    ServiceProviderDAO createSPDAO();
}
