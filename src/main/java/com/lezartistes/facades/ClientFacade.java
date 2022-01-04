package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.ClientDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.models.Client;

import java.util.List;

public class ClientFacade {

    /*attributes*/
    private static ClientFacade clientFacade;
    private final ClientDAO clientDao;

    /*constructor*/
    private ClientFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.clientDao = factory.createClientDAO();
    };

    /*methods*/
    public static ClientFacade getInstance(){
        if(clientFacade == null){
            clientFacade = new ClientFacade();
        }
        return clientFacade;
    }
    public List<Client> getAllClients() throws ClientNotFoundException{
        return this.clientDao.getAllClients();
    }

    public Client getClientById(int id) throws ClientNotFoundException {
        return this.clientDao.getClientById(id);
    }

    public Client getClientByEmail(String email) {
        return this.clientDao.getClientByEmail(email);
    }


    public int createClient(Client c) {
        return this.clientDao.createClient(c);
    }
}

