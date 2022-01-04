package com.lezartistes.controllers;

import com.lezartistes.App;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.facades.UserFacade;
import com.lezartistes.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


public class AccueilController {

    @FXML protected void redirectToHome(ActionEvent event) throws IOException {
        App.setRoot("views/");
    }

}
