package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.quotation.QuotationDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.models.Quotation;

import java.util.List;

public class QuotationFacade {

    private static QuotationFacade quotationFacade;
    private QuotationDAO quotationDAO;

    private QuotationFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.quotationDAO = factory.createQuotationDAO();
    }

    public static QuotationFacade getInstance(){
        if(quotationFacade==null){
            quotationFacade = new QuotationFacade();
        }
        return quotationFacade;
    }

    public Quotation getQuotationById(int id) throws QuotationNotFoundException {
        return this.quotationDAO.getQuotationById(id);
    }

    public List<Quotation> getAllQuotation() throws QuotationNotFoundException{
        return this.quotationDAO.getAllQuotation();
    }

    public List<Quotation> getQuotationByCompany(int idc) throws QuotationNotFoundException{
        return this.quotationDAO.getQuotationByCompany(idc);
    }

    public List<Quotation> getQuotationByCAP(int idcap) throws QuotationNotFoundException{
        return this.quotationDAO.getQuotationByCAP(idcap);
    }

    public Quotation createQuotation(Quotation quotation) {
        return this.quotationDAO.createQuotation(quotation);
    }

    public Quotation deleteQuotation(int id) throws QuotationNotFoundException {
        return this.quotationDAO.deleteQuotation(id);
    }
}
