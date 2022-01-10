package com.lezartistes.tests;

import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.facades.QuotationFacade;
import com.lezartistes.models.Building;
import com.lezartistes.models.Quotation;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

public class TestQuotation {
    @Test
    public void addAndDeleteQuotation() throws QuotationNotFoundException {
        QuotationFacade quotationFacade = QuotationFacade.getInstance();

        Quotation q = new Quotation(1, "Test","Test",2,1,1,1,1,1);
        quotationFacade.createQuotation(q);

        Assertions.assertTrue(quotationFacade.deleteQuotationByTitle("Test") != 0);

    }
}
