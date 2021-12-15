package com.lezartistes.dao;

public interface AbstractFactory {

    /**
     * Create and return a UserDAO
     * @return a UserDAO
     */
    UserDAO createUserDAO();

    QuotationDAO createQuotationDAO();
}
