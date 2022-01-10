package com.lezartistes.controllers.quotation;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.models.Quotation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ReadQuotationController extends GeneralController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Label expert;

    @FXML
    private Label capital;

    @FXML
    private Label siret_number;

    @FXML
    private Label number_business_register;

    @FXML
    private Label NAF;

    @FXML
    private Label total_price_TTC;

    Quotation quotation;
    public ReadQuotationController(Quotation selectedQuot) {
        this.quotation=selectedQuot;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.title.setText(this.quotation.getTitle());
        this.expert.setText(this.quotation.getExpert());
        this.capital.setText(String.valueOf(this.quotation.getCapital()));
        this.siret_number.setText(String.valueOf(this.quotation.getSiret_number()));
        this.number_business_register.setText(String.valueOf(this.quotation.getNumber_business_register()));
        this.NAF.setText(String.valueOf(this.quotation.getNAF()));
        this.total_price_TTC.setText(String.valueOf(this.quotation.getTotal_price_TTC()));
    }
}
