package com.lezartistes.dao.quotation;

import java.sql.Connection;

public class QuotationDAOPostgres extends QuotationDAO{

    private static QuotationDAOPostgres quotationDAOPostgres;
    private Connection coToDB;

    private QuotationDAOPostgres(Connection connection){
        this.coToDB=connection;
    }

    public static QuotationDAOPostgres getInstance(Connection connection){
        if(quotationDAOPostgres == null){
            quotationDAOPostgres = new QuotationDAOPostgres(connection);
        }
        return quotationDAOPostgres;
    }
}
