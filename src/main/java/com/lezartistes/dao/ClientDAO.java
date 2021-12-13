package com.lezartistes.dao;

import com.lezartistes.models.Client;

import java.io.Serializable;

public abstract class ClientDAO implements Serializable {

    public ClientDAO(){ super();}

    public abstract Client[] getAllClients();

    public abstract Client getClientById(int id);
}
