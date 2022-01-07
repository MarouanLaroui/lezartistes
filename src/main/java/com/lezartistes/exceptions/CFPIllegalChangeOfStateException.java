package com.lezartistes.exceptions;

public class CFPIllegalChangeOfStateException extends Exception{
    public CFPIllegalChangeOfStateException(){
        super("Changement d'état de modération illégal.");
    }

    public CFPIllegalChangeOfStateException(String actualStatus, String newStatus){
        super("Changement d'état de "+ actualStatus + " vers "+ newStatus +" impossible.");
    }
}
