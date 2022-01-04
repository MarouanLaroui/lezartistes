package com.lezartistes.exceptions;

public class ReportNotFoundException extends Exception{

    public ReportNotFoundException(){
        super("No reports were found in the db");
    }
    public ReportNotFoundException(int id){
        super("No report was found in the db with an id of "+id);
    }
}
