package com.lezartistes.controllers.user;

import com.lezartistes.App;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.facades.UserFacade;
import com.lezartistes.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {
    /**
     * Linked to the TextField who's fx:id is username
     */
    @FXML
    private TextField username;

    /**
     * Linked to the TextField who's fx:id is password
     */
    @FXML
    private TextField password;

    /**
     * The UserFacade used to communicate and treat data.
     */
    private final UserFacade userfacade = new UserFacade();

    /**
     * Fonction appelée par notre bouton lorsqu'il reçoit une action
     * @param event the event detected
     */
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        System.out.println("Message encrypté : " + username.getText());
        //Will be used later
        User ourUser;
        try {
            ourUser = userfacade.login(username.getText(), password.getText());
            System.out.println("Vous avez réussi à vous connecter");
        }
        catch (UserNotFoundException e) {
            ourUser = null;
            System.out.println("L'utilisateur n'existe pas");
        }

    }

    @FXML protected void switchToSignIn (ActionEvent e) throws IOException {
        App.setRoot("views/user/signIn");
    }
}
