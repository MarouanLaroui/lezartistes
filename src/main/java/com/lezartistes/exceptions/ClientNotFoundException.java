package com.lezartistes.exceptions;

public class ClientNotFoundException extends Exception{
    /*constructor*/
    public ClientNotFoundException () {
        super("Aucun clients n'a été trouvé dans la base de donnée");
    }
    public ClientNotFoundException (int id) {
        super("Le client " + id + " n'a pas été trouvé dans la base de donnée");
    }

    public ClientNotFoundException (String email) {
        super("Le client avec le mail " + email + " n'a pas été trouvé dans la base de donnée");
    }
}
