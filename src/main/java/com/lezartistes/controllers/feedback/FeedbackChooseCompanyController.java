package com.lezartistes.controllers.feedback;

import com.lezartistes.models.Company;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FeedbackChooseCompanyController implements Initializable {

    @FXML
    private ListView<Company> companiesList;
    @FXML
    private TextField searchInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
