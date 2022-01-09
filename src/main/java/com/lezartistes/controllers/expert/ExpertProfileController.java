package com.lezartistes.controllers.expert;

import com.lezartistes.App;
import com.lezartistes.facades.ExpertFacade;
import com.lezartistes.models.Company;
import com.lezartistes.models.Expert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExpertProfileController implements Initializable {

    private Expert expert;

    /**
     * Linked to the TextField who's fx:id is his name
     */
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label mail;
    @FXML
    private Label company_name;
    @FXML
    private Label city;
    @FXML
    private Label postal_code;

    private final ExpertFacade expertFacade;
    private Stage stage;

    public ExpertProfileController(Expert expert, Stage stage){
        this.expert = expert;
        this.expertFacade = ExpertFacade.getInstance();
        this.stage = stage;
    }

    //TODO : Mettre les affichages dans le bon field dans le fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*Displaying the retrieved informations*/

        this.name.setText(this.expert.getMail());
        this.surname.setText(this.expert.getSurname());
        this.mail.setText(this.expert.getMail());
        Company expertCompany = this.expert.getCompany();
        this.city.setText(expertCompany.getCity());
        this.company_name.setText(expertCompany.getName());
        this.postal_code.setText(String.valueOf(expertCompany.getPostal_code()));



    }

    public void redirectToExpertList(MouseEvent mouseEvent) {
        try {
            App.setRoot("ExpertList");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
