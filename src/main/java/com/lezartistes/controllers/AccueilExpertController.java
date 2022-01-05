package com.lezartistes.controllers;

import com.lezartistes.App;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccueilExpertController implements Initializable {



    @FXML
    protected void redirectToHome(ActionEvent event) throws IOException {
        User user = UserInformation.getUser();
        System.out.println(user == null);
        App.setRoot("views/");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}



