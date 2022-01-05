package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.quotation.QuotationDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.models.Quotation;


import java.io.FileNotFoundException;
import java.util.List;

public class QuotationFacade {

    private QuotationFacade quotationFacade;
    private QuotationDAO quotationDAO;

    private QuotationFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.quotationDAO = factory.createQuotationDAO();
    }

    public QuotationFacade getInstance(){
        if(quotationFacade==null){
            quotationFacade = new QuotationFacade();
        }
        return quotationFacade;
    }

    public Quotation getQuotationById(int id) throws QuotationNotFoundException {
        return this.quotationDAO.getQuotationById(id);
    }

    public List<Quotation> getAllReports() throws QuotationNotFoundException{
        return this.quotationDAO.getAllQuotation();
    }

    public List<Quotation> getReportsByAuthor(int id) throws QuotationNotFoundException{
        //TODO : CREATE THE REAL FUNCTION
        return this.quotationDAO.getAllQuotation();
    }

    public List<Quotation> getReportsForProposal(int callForProposalId) throws QuotationNotFoundException{
        //TODO : CREATE THE REAL FUNCTION
        return this.quotationDAO.getAllQuotation();
    }

    public Quotation createReport(Quotation report) throws FileNotFoundException {
        return this.quotationDAO.createQuotation(report);
    }

    public Quotation deleteReport(int id) throws QuotationNotFoundException {
        return this.quotationDAO.deleteQuotation(id);
    }
}
