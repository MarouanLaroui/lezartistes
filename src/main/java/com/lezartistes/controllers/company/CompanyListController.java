package com.lezartistes.controllers.company;

import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Company;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CompanyListController implements Initializable {
    /*attributes*/
    CompanyFacade companyFacade = CompanyFacade.getInstance();
    List<Company> companies = new ArrayList<Company>();
    private Stage stage;

    @FXML
    private ListView<Company> companiesList;
    @FXML
    private TextField searchInput;

    /*methods*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.companies = companyFacade.getAllCompanies();
            this.companiesList.setItems(new FilteredList<>(FXCollections.observableList(this.companies)));
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
    }
}
