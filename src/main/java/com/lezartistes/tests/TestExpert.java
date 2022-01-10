package com.lezartistes.tests;

import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.ExpertNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.facades.ExpertFacade;
import com.lezartistes.models.Company;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class TestExpert {
    @Test
    public void addAndDeleteExpert() throws ExpertNotFoundException, CompanyNotFoundException {
        ExpertFacade expertFacade = ExpertFacade.getInstance();
        CompanyFacade companyFacade = CompanyFacade.getInstance();
        int nbExpertsBeforeAdd = expertFacade.getAllExperts().size();
        Company c = companyFacade.getCompanyById(1);

        expertFacade.createExpert("test@gmail.com","123", "NameExpert", "SurnameExpert", c);

        Assertions.assertTrue(nbExpertsBeforeAdd < expertFacade.getAllExperts().size());
        expertFacade.deleteExpertByMail("test@gmail.com");
    }
}