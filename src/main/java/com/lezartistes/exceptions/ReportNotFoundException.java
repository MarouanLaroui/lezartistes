package com.lezartistes.exceptions;

public class ReportNotFoundException extends Exception{
    public ReportNotFoundException(int id){
        super("No report was found in the db with an id of "+id);
    }
}
