package com.lezartistes.dao;

import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.models.Client;

import java.io.Serializable;
import java.util.List;

public abstract class ClientDAO implements Serializable {

    public ClientDAO(){ super();}

    public abstract List<Client> getAllClients() throws ClientNotFoundException;

    public abstract Client getClientById(int id) throws ClientNotFoundException;
}
