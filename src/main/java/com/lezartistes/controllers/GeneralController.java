package com.lezartistes.controllers;

import com.lezartistes.App;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public abstract class GeneralController {

    @FXML protected void redirectToHome() throws IOException {
        if (UserInformation.isServiceProvider())
            App.setRoot("views/accueilExpert");
        else
            App.setRoot("views/accueilClient");
    }

    @FXML protected void redirectToCompanyList() throws IOException{
        App.setRoot("views/company/companyList");
    }

    @FXML protected void redirectToCFPList() throws IOException {
        App.setRoot("views/callForProposal/callForProposalList");
    }

    @FXML protected void redirectToClientList() throws IOException {
        App.setRoot("views/client/ClientList");
    }

    @FXML protected void goToHistory() throws IOException{
        App.setRoot("views/history/HistoryList");
    }

    @FXML protected void redirectToBuildingList () throws IOException {
        App.setRoot("views/building/listBuilding");
    }

}
