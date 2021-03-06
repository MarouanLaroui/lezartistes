package com.lezartistes.dao;

import com.lezartistes.dao.callForProposal.CallForProposalDAO;
import com.lezartistes.dao.building.BuildingDAO;
import com.lezartistes.dao.expert.ExpertDAO;
import com.lezartistes.dao.expert.ExpertDAOPostgres;
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
     * @return a CompanyDAO
     */
    CompanyDAO createCompanyDAO();

    /**
     * Create and return a CallForProposalDAO
     * @return a CallForProposalDAO
     */
    CallForProposalDAO createCallForProposalDAO();

    ClientDAO createClientDAO();

    ReportDAO createReportDAO();

    QuotationDAO createQuotationDAO();

    ExpertDAO createExpertDAO();

    ServiceProviderDAO createSPDAO();

    HistoryDAO createHistoryDAO();

    BuildingDAO createBuildingDAO();
}

