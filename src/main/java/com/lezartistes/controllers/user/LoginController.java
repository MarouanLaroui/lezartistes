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
        //Will be used later
        User ourUser;
        try {
            ourUser = userfacade.login(username.getText(), password.getText());
            UserInformation.setUser(ourUser);

            System.out.println("Vous avez réussi à vous connecter");
            System.out.println("Votre user est il un SP ? " + UserInformation.isServiceProvider());
            if ( ! UserInformation.isServiceProvider())
                App.setRoot("views/accueilClient");
            else
                App.setRoot("views/accueilExpert");

        } catch (UserNotFoundException e) {
            System.out.println("L'utilisateur n'existe pas");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("On a un problème de redirection vers l'accueil");
        }
    }

    @FXML protected void switchToSignIn (ActionEvent e) throws IOException {
        App.setRoot("views/user/signIn");
    }
}
