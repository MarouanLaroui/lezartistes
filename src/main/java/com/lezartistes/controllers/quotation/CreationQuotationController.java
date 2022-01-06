package com.lezartistes.controllers.quotation;

import com.lezartistes.facades.QuotationFacade;
import com.lezartistes.models.Quotation;
import com.lezartistes.models.Report;
import com.lezartistes.validation.InputControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    public void createQuotation(ActionEvent actionEvent) {

            Quotation newQuotation = null;
            try {
                newQuotation = new Quotation(
                        Integer.parseInt(company.getText()),
                        this.title.getText(),
                        Integer.parseInt(expert.getText()),
                        Double.parseDouble(capital.getText()),
                        Integer.parseInt(siret_number.getText()),
                        Integer.parseInt(number_business_register.getText()),
                        Integer.parseInt(NAF.getText()),
                        Double.parseDouble(total_price_TTC.getText()),
                        Integer.parseInt();
                        );
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.quotationFacade(newQuotation);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }


}
