package com.lezartistes.dao;

import com.lezartistes.dao.feedback.FeedbackDAO;

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
}