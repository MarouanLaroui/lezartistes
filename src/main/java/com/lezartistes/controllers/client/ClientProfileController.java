package com.lezartistes.controllers.client;


import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientProfileController implements Initializable {

    private Client client;

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
    private Stage stage;

    public ClientProfileController(Client client, Stage stage){
        this.client = client;
        this.clientFacade = ClientFacade.getInstance();
        this.stage = stage;
    }

    //TODO : Mettre les affichages dans le bon field dans le fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*Displaying the retrieved informations*/

        name.setText(this.client.getName());
        surname.setText(this.client.getSurname());
        street.setText(this.client.getStreet());
        complement.setText(this.client.getComplement());
        city.setText(this.client.getCity());
        postal_code.setText(String.valueOf(this.client.getPostal_code()));
        phone_number.setText(String.valueOf(this.client.getPhone_number()));



    }
}
