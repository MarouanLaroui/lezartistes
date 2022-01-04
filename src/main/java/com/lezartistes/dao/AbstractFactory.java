package com.lezartistes.dao;

<<<<<<< HEAD
import com.lezartistes.dao.feedback.FeedbackDAO;
=======
import com.lezartistes.dao.serviceProvider.ServiceProviderDAO;
>>>>>>> master

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
    ClientDAO createClientDAO();

    ReportDAO createReportDAO();

    ServiceProviderDAO createSPDAO();
}