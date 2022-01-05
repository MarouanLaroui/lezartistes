package com.lezartistes.dao;

import com.lezartistes.dao.history.HistoryDAO;
import com.lezartistes.dao.company.CompanyDAO;
import com.lezartistes.dao.quotation.QuotationDAO;
import com.lezartistes.dao.feedback.FeedbackDAO;
import com.lezartistes.dao.serviceProvider.ServiceProviderDAO;

public interface AbstractFactory {

    /**
     * Create and return a UserDAO
     * @return a UserDAO
     */
    UserDAO createUserDAO();

    /**
     * Create and return a FeedbackDAO
     * @return a FeedbackDAO
     */
    FeedbackDAO createFeedbackDAO();

    /**
     * Create and return a CompanyDAO
     * @return
     */
    CompanyDAO createCompanyDAO();

    ClientDAO createClientDAO();

    ReportDAO createReportDAO();

    QuotationDAO createQuotationDAO();

    ServiceProviderDAO createSPDAO();

    HistoryDAO createHistoryDAO();
}

