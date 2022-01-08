package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.serviceProvider.ServiceProviderDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.models.Company;
import com.lezartistes.models.Expert;
import com.lezartistes.models.User;

public class ServiceProviderFacade {
    //TODO : Mettre en place une interface Singleton pour définir la méthode getInstance

    /*attributes*/
    private static ServiceProviderFacade spFacade = null;
    private final ServiceProviderDAO spDAO;

    /*constructor*/

    private ServiceProviderFacade() {
        AbstractFactory fact = PostgresFactory.getInstance();
        this.spDAO = fact.createSPDAO();
    }

    /*methods*/
    public static ServiceProviderFacade getInstance() {
        if (spFacade == null)
            spFacade = new ServiceProviderFacade();
        return spFacade;
    }

    public int createServiceProvider(String username, String passwd, Company c) {
        //TODO : La Company dans les experts
        return this.spDAO.createServiceProvider(new Expert(username, passwd, c, true));
    }

    public Expert getServiceProviderByEmail(String mail) {
        return this.spDAO.getServiceProviderByEmail(mail);
    }
}
