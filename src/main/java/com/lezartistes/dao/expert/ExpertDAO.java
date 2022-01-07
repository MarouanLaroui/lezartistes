package com.lezartistes.dao.expert;

import com.lezartistes.models.Expert;

import java.io.Serializable;
import java.util.List;

public abstract class ExpertDAO implements Serializable {

    public ExpertDAO(){}

    public abstract List<Expert> getAllExperts();

    public abstract Expert getExpertById(int id);

    public abstract int createExpert(Expert expert);

    /*
    * public abstract List<Client> getAllClients() throws ClientNotFoundException;

    public abstract Client getClientById(int id) throws ClientNotFoundException;

    public abstract Client getClientByEmail(String email);

    public abstract int createClient(Client c);
    * */
}
