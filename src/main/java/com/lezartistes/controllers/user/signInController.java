package com.lezartistes.controllers.user;

import com.lezartistes.App;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.facades.UserFacade;
import com.lezartistes.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class signInController {

    /**
     * The UserFacade used to communicate and treat data.
     */
    private final UserFacade userfacade = new UserFacade();

    @FXML protected void clientSide(ActionEvent event) {

    }

    @FXML protected void expertSide(ActionEvent event) {

    }
}