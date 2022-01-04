package com.lezartistes.exceptions;

public class QuotationNotFoundException extends Exception{
    public QuotationNotFoundException (int id_quotation){
        super("No quotation found for the id :"+id_quotation);
    }
}
