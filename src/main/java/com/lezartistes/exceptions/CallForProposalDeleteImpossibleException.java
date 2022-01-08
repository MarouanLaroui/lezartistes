package com.lezartistes.exceptions;

public class CallForProposalDeleteImpossibleException extends Exception{
    public CallForProposalDeleteImpossibleException(){super("Impossible de supprimer un appel d'offre qui a un rapport associé.");}
    public CallForProposalDeleteImpossibleException(int id){
        super("Impossible de supprimer l'appel d'offre" + id + " car il a un rapport associé.");
    }

}
