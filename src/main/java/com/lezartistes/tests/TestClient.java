package com.lezartistes.tests;

import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.Client;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class TestClient {

    @Test
    public void add() throws ClientNotFoundException {
        ClientFacade clientFacade = ClientFacade.getInstance();

        Client c = new Client("mail","pswd",true);
        clientFacade.createClient(c);

        Assertions.assertTrue(clientFacade.getAllClients().contains(c));

    }

}
