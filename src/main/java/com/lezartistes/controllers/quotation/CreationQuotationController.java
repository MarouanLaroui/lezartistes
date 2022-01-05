package com.lezartistes.controllers.quotation;

import com.lezartistes.facades.QuotationFacade;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreationQuotationController {

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

    private QuotationFacade quotationFacade;

    public CreationQuotationController(){
        this.quotationFacade = QuotationFacade.getInstance();
    }


}
