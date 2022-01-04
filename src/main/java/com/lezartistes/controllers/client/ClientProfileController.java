package com.lezartistes.controllers.client;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientProfileController {

    /**
     * Linked to the TextField who's fx:id is his name
     */
    @FXML
    private Label  name;

    @FXML
    private Label  surname;

    @FXML
    private Label  street;

    @FXML
    private Label  complement;

    @FXML
    private Label  city;

    @FXML
    private Label  postal_code;

    @FXML
    private Label phone_number;

    private final ClientFacade clientFacade;

    public ClientProfileController(){

        this.clientFacade = ClientFacade.getInstance();
    }

    @FXML protected void displayClientInformation(int id){

        /*Request to get information of the Client*/
        AbstractFactory factory = PostgresFactory.getInstance();
        try{
            Client client = factory.createClientDAO().getClientById(id);

            /*Displaying the retrieved informations*/
            name.setText(client.getName());
            surname.setText(client.getSurname());
            street.setText(client.getStreet());
            complement.setText(client.getComplement());
            city.setText(client.getCity());
            postal_code.setText(String.valueOf(client.getPostal_code()));
            phone_number.setText(String.valueOf(client.getPhone_number()));
        }
        catch (ClientNotFoundException e){
            e.printStackTrace();
        }
    }

    @FXML protected void displayClientInformation(Client client){

        /*Displaying the retrieved informations*/
        name.setText(client.getName());
        surname.setText(client.getSurname());
        street.setText(client.getStreet());
        complement.setText(client.getComplement());
        city.setText(client.getCity());
        postal_code.setText(String.valueOf(client.getPostal_code()));
        phone_number.setText(String.valueOf(client.getPhone_number()));

    }

}
