package com.lezartistes.dao;

import com.lezartistes.models.Client;

import java.sql.Connection;

public class ClientDAOPostgres extends ClientDAO{

    /*attributes*/
    private static ClientDAOPostgres clientDAOPostgres;
    private Connection coToDB;

    private ClientDAOPostgres(Connection connection){
        this.coToDB = connection;
    }


    public static ClientDAOPostgres getInstance(Connection connection){
        if(clientDAOPostgres == null){
            clientDAOPostgres = new ClientDAOPostgres(connection);
        }
        return clientDAOPostgres;
    }


    @Override
    public Client[] getAllClients() {
        return new Client[0];
    }

    @Override
    public Client getClientById(int id) {
        return null;
    }
}
