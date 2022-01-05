package com.lezartistes.controllers.company;

import com.lezartistes.App;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Company;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompanyProfileController implements Initializable {

    /**
     * Linked to the TextField who's fx:id is his name
     */
    @FXML
    private Label name;

    @FXML
    private Label departement;

    @FXML
    private Label street;

    @FXML
    private Label city;

    @FXML
    private Label postalCode;

    @FXML
    private Label complement;

    private Company company;
    private final CompanyFacade companyFacade;
    private Stage stage;

    public CompanyProfileController(Company company, Stage stage){
        this.company = company;
        this.companyFacade = CompanyFacade.getInstance();
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Displaying the retrieved informations*/
        name.setText(this.company.getName());
        departement.setText(this.company.getDepartement());
        street.setText(this.company.getStreet());
        city.setText(this.company.getCity());
        postalCode.setText(String.valueOf(this.company.getPostal_code()));
        complement.setText(this.company.getComplement());

    }

    public void redirectToCompanyList(MouseEvent mouseEvent) {
        try {
            App.setRoot("views/companyList");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
