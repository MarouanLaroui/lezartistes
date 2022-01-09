package com.lezartistes.controllers.user;

import com.lezartistes.App;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.facades.*;
import com.lezartistes.models.Client;
import com.lezartistes.models.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class SignInController {

    /**
     * The UserFacade used to communicate and treat data.
     */
    private final UserFacade userfacade = new UserFacade();

    private final ExpertFacade expertFacade = ExpertFacade.getInstance();
    private final ClientFacade clientFacade = ClientFacade.getInstance();
    private final CompanyFacade companyFacade = CompanyFacade.getInstance();

    // Expert side
    @FXML
    private TextField username_expert;
    @FXML
    private TextField password_expert;
    @FXML
    private TextField name_expert;
    @FXML
    private TextField surname_expert;
    @FXML
    private ComboBox<Company> companyExpert;

    @FXML
    private TextField username_client;
    @FXML
    private TextField password_client;
    @FXML
    private TextField name_client;
    @FXML
    private TextField surname_client;
    @FXML
    private TextField street_client;
    @FXML
    private TextField complement_client;
    @FXML
    private TextField city_client;
    @FXML
    private TextField postal_code_client;
    @FXML
    private TextField phone_number_client;
    @FXML
    private Button back;

    @FXML protected void clientSide(ActionEvent event) throws Exception {
        App.setRoot("views/user/signInClient");
    }

    @FXML protected void expertSide(ActionEvent event) throws Exception {
        App.setRoot("views/user/signInExpert");
    }

    @FXML protected void getCompanies() {
        ArrayList<Company> companies = new ArrayList<>();
        try {
            companies = new ArrayList<>(this.companyFacade.getAllCompanies());
        } catch (CompanyNotFoundException ignored) {}
        ObservableList<Company> options = FXCollections.observableArrayList(companies);
        this.companyExpert.getItems().addAll(options);
    }

    @FXML protected void validateExpertCreation(ActionEvent e) throws IOException {
        System.out.println("NOM de l'expert : " + this.name_expert.getText());
        int ret = expertFacade.createExpert(
                username_expert.getText(),
                password_expert.getText(),
                name_expert.getText(),
                surname_expert.getText(),
                this.companyExpert.getValue());
        //ret = 1 -> utilisateur crée
        //ret = 0 -> utilisateur non crée
        System.out.println("Retour au départ et les news sont : " + ret);
        if (ret == 1) //Si son compte est correctement crée on le redirige vers la page de connexion
            App.setRoot("views/user/login");
    }

    @FXML protected void validateClientCreation (ActionEvent e) throws IOException {
        int ret = this.clientFacade.createClient(new Client(
                username_client.getText(),
                password_client.getText(),
                name_client.getText(),
                surname_client.getText(),
                street_client.getText(),
                complement_client.getText(),
                city_client.getText(),
                Integer.parseInt(postal_code_client.getText()),
                Integer.parseInt(phone_number_client.getText()),
                true
        ));
        System.out.println("Résultat de notre requête de création de client : " + ret);

        if (ret == 1)
            App.setRoot("views/user/login");
    }

    @FXML
    public void redirectToLogin(MouseEvent event) throws IOException {
        App.setRoot("views/user/login");
    }
}
