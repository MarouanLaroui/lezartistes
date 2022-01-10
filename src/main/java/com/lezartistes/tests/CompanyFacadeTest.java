package com.lezartistes.tests;

import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Company;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CompanyFacadeTest {
    @Test
    public void createCompany() {
        CompanyFacade facade = CompanyFacade.getInstance();
        Assertions.assertEquals(1, facade.addCompany(new Company(500, "Polytech", "Hérault", "Rue des srtistes", "Montpellier", "", 34090)));
        facade.deleteCompany(500);
    }
}
