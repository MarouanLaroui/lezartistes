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


    @FXML protected void redirectToClientList() throws IOException {
        App.setRoot("views/client/ClientList");
    }
}
