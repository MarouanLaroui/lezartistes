package com.lezartistes.exceptions;

public class CompanyNotFoundException extends Exception{
    /*constructor*/
    public CompanyNotFoundException() {
        super("Aucune entreprise n'a été trouvée dans la base de donnée.");
    }
    public CompanyNotFoundException(String name) {
        super("L'entreprise " + name + " n'a pas été trouvée dans la base de donnée.");
    }
    public CompanyNotFoundException(int idCompany) {
        super("L'entreprise d'identifiant" + idCompany + " n'a pas été trouvée dans la base de donnée.");
    }
}
