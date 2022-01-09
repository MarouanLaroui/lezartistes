package com.lezartistes.controllers.user;

import com.lezartistes.App;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.facades.ServiceProviderFacade;
import com.lezartistes.facades.UserFacade;
import com.lezartistes.models.Building;
import com.lezartistes.models.Client;
import com.lezartistes.models.Company;
import com.lezartistes.models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignInController {

    /**
     * The UserFacade used to communicate and treat data.
     */
    private final UserFacade userfacade = new UserFacade();

    private final ServiceProviderFacade spFacade = ServiceProviderFacade.getInstance();
    private final ClientFacade clientFacade = ClientFacade.getInstance();
    private final CompanyFacade companyFacade = CompanyFacade.getInstance();

    // Expert side
    @FXML
    private TextField username_expert;
    @FXML
    private TextField password_expert;
    @FXML
    private ComboBox<Company> companySP;

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

    @FXML protected void mouseMoveDetected() {
        ArrayList<Company> companies = new ArrayList<>();
        try {
            companies = new ArrayList<>(this.companyFacade.getAllCompanies());
        } catch (CompanyNotFoundException ignored) {}
        ObservableList<Company> options = FXCollections.observableArrayList(companies);
        this.companySP.getItems().addAll(options);
    }

    @FXML protected void validateExpertCreation(ActionEvent e) throws IOException {
        int ret = spFacade.createServiceProvider(username_expert.getText(), password_expert.getText(), this.companySP.getValue());
        //ret = 1 -> utilisateur crée
        //ret = 0 -> utilisateur non crée
        if (ret == 1) //Si son compte est correctement crée on le redirige vers la page de connexion
            App.setRoot("views/user/login");
        //TODO : Sinon il faudrait traiter l'erreur
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
