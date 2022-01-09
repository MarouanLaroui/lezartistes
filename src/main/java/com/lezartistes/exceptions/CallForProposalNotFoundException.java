package com.lezartistes.exceptions;

public class CallForProposalNotFoundException extends Exception{
    /*constructor*/
    public CallForProposalNotFoundException () {
        super("Aucun appel d'offre (call for proposal) n'a été trouvé dans la base de donnée");
    }

    public CallForProposalNotFoundException (int id) {
        super("L'appel d'offre " + id + " n'a pas été trouvé dans la base de donnée");
    }

    public CallForProposalNotFoundException (String authorMail) {
        super("Aucun appel d'offre pour l'auteur " + authorMail + " n'a pas été trouvé dans la base de donnée");
    }
}
