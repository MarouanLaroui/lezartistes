package com.lezartistes.controllers.quotation;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.facades.CallForProposalFacade;
import com.lezartistes.facades.ExpertFacade;
import com.lezartistes.facades.QuotationFacade;
import com.lezartistes.models.*;
import com.lezartistes.validation.InputControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreationQuotationController extends GeneralController implements Initializable {


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

    @FXML
    private ComboBox<String> cfp;

    private QuotationFacade quotationFacade;
    private final ExpertFacade expertFacade;
    private final CallForProposalFacade cfpFacade;
    private Label error;

    public CreationQuotationController(){
        this.quotationFacade = QuotationFacade.getInstance();
        this.expertFacade = ExpertFacade.getInstance();
        this.cfpFacade=CallForProposalFacade.getInstance();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //initilisation des buildings
            java.util.List<CallForProposal> cfpToDisplay = this.cfpFacade.getAllPostedAndOverCallForProposal();
            List<String> cfptitle = new ArrayList<>();
            for (CallForProposal c : cfpToDisplay) {
                cfptitle.add(c.getTitle());
            }

            ObservableList<String> optionsB = FXCollections.observableArrayList(cfptitle);
            this.cfp.getItems().addAll(optionsB);

        } catch (CallForProposalNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createQuotation(ActionEvent actionEvent) throws CallForProposalNotFoundException {
        if(title.getText().equals("") ){
            this.error.setText("Please give this call for proposal a title and a description.");
        }
        else {
            User user = this.expertFacade.getExpertByEmail(UserInformation.getUser().getMail());
            String author = this.expertFacade.getExpertByEmail(user.getMail()).getName();
            int idcompany = this.expertFacade.getExpertByEmail(user.getMail()).getCompany().getId_company();

            String cfpChosen = this.cfp.getValue();
            int idcfp = this.cfpFacade.getCallForProposalIdByTitle(cfpChosen);

            Quotation q = new Quotation(
                    idcompany,
                    this.title.getText(),
                    author,
                    Double.parseDouble(this.capital.getText()),
                    Integer.parseInt(this.siret_number.getText()),
                    Integer.parseInt(this.number_business_register.getText()),
                    Integer.parseInt(this.NAF.getText()),
                    Double.parseDouble(this.total_price_TTC.getText()),
                    idcfp
            );
            Quotation quot = this.quotationFacade.createQuotation(q);
        }

    }


}
