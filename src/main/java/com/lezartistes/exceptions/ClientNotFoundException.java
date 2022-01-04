package com.lezartistes.exceptions;

public class ClientNotFoundException extends Exception{
    /*constructor*/
    public ClientNotFoundException (int id) {
        super("Le client " + id + " n'a pas été trouvé dans la base de donnée");
    }
}