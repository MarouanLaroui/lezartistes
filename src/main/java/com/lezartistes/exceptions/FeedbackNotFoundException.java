package com.lezartistes.exceptions;

public class FeedbackNotFoundException extends Exception{
    /*constructor*/
    public FeedbackNotFoundException () {
        super("Aucun feedback n'a été trouvé dans la base de donnée.");
    }
    public FeedbackNotFoundException (int id) {
        super("Le feedback " + id + " n'a pas été trouvé dans la base de donnée.");
    }


}
