package com.lezartistes.exceptions;

public class UserNotFoundException extends Exception{
    /*constructor*/
    public UserNotFoundException () {
        super("La connexion de l'utilisateur à échoué. \n" +
                "Il n'a pas été trouvé");
    }

    public UserNotFoundException(String email) {
        super("La connexion de l'utilisateur " + email + " à échoué. \n" +
                "Il n'a pas été trouvé");
    }
}
