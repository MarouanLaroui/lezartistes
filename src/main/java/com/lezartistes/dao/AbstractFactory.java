package com.lezartistes.dao;

public interface AbstractFactory {

    /**
     * Create and return a UserDAO
     * @return a UserDAO
     */
    UserDAO createUserDAO();

<<<<<<< HEAD
    QuotationDAO createQuotationDAO();
}
=======
    ClientDAO createClientDAO();

     ReportDAO createReportDAO();

}
>>>>>>> master
