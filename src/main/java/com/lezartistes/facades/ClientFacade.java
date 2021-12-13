package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.ClientDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.models.Client;

public class ClientFacade {

    /*attributes*/
    private static ClientFacade clientFacade;
    private ClientDAO clientDao;

    /*constructor*/
    private ClientFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.clientDao =  factory.createClientDAO();
    };

    /*methods*/
    public static ClientFacade getInstance(){
        if(clientFacade == null){
            clientFacade = new ClientFacade();
        }
        return clientFacade;
    }
    public Client[] getAllClients(){
        return this.clientDao.getAllClients();
    }

    public Client getClientById(int id){
        return this.clientDao.getClientById(id);
    }


}

