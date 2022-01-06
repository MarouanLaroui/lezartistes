package com.lezartistes.dao.quotation;

import java.io.Serializable;
import java.util.List;

import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.models.Quotation;

public abstract class QuotationDAO implements Serializable {

    public QuotationDAO(){
        super();
    }

    public abstract List<Quotation> getAllQuotation() throws QuotationNotFoundException;

    public abstract Quotation getQuotationById(int id) throws QuotationNotFoundException;

    public abstract Quotation createQuotation(Quotation quotation);

    public abstract Quotation deleteQuotation(int id) throws QuotationNotFoundException;

    public abstract List<Quotation> getQuotationByCAP(int idcap) throws QuotationNotFoundException;

    public abstract List<Quotation> getQuotationByCompany(int idc) throws QuotationNotFoundException;

}
