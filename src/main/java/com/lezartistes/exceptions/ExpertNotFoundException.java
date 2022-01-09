package com.lezartistes.exceptions;

public class ExpertNotFoundException extends Exception{

    public ExpertNotFoundException(int id){
        super("L'expert " + id + " n'a pas été trouvé dans la base de donnée");
    }

    public ExpertNotFoundException(){
        super("Pas d'expert n'a pas été trouvé dans la base de donnée");
    }
}