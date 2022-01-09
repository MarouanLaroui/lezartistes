package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.ClientDAO;
import com.lezartistes.dao.expert.ExpertDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.exceptions.ExpertNotFoundException;
import com.lezartistes.models.Company;
import com.lezartistes.models.Expert;

import java.util.List;

public class ExpertFacade {
    /*attributes*/
    private static ExpertFacade expertFacade;
    private final ExpertDAO expertDAO;

    /*constructor*/
    private ExpertFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.expertDAO = factory.createExpertDAO();
    };

    /**/
    public static  ExpertFacade getInstance(){
        if(expertFacade == null){
            expertFacade = new ExpertFacade();
        }
        return expertFacade;
    }

    public List<Expert> getAllExperts() throws ExpertNotFoundException {
        return this.expertDAO.getAllExperts();
    }

    public Expert getExpert(int id) throws ExpertNotFoundException{
        return this.expertDAO.getExpertById(id);
    }

    public int createExpert(String username, String passwd, String name, String surname , Company c) {
        return this.expertDAO.createExpert(new Expert(username, passwd, name, surname, c, true));
    }

    public Expert getExpertByEmail(String mail) {
        return this.expertDAO.getExpertByEmail(mail);
    }
}
