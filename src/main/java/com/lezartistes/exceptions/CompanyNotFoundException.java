package com.lezartistes.exceptions;

public class CompanyNotFoundException extends Exception{
    /*constructor*/
    public CompanyNotFoundException() {
        super("Aucune entreprise n'a été trouvé dans la base de donnée.");
    }
    public CompanyNotFoundException(String name) {
        super("L'entreprise " + name + " n'a pas été trouvé dans la base de donnée.");
    }

}
