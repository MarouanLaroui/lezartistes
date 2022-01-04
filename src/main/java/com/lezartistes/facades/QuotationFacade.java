package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.quotation.QuotationDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.models.Quotation;

public class QuotationFacade {

    private QuotationFacade quotationFacade;
    private QuotationDAO quotationDAO;

    public QuotationFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.quotationDAO = factory.createQuotationDAO();
    }

    public QuotationFacade getInstance(){
        if(quotationFacade==null){
            quotationFacade = new QuotationFacade();
        }
        return quotationFacade;
    }
}
