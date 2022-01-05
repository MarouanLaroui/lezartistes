package com.lezartistes.controllers.quotation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModificationQuotationController implements Initializable {
    @FXML
    private TextField company;

    @FXML
    private TextField title;

    @FXML
    private TextField expert;

    @FXML
    private TextField capital;

    @FXML
    private TextField siret_number;

    @FXML
    private TextField number_business_register;

    @FXML
    private TextField NAF;

    @FXML
    private TextField total_price_TTC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
