package com.lezartistes.exceptions;

public class BuildingNotFoundException extends Exception{

    public BuildingNotFoundException(){
        super("No buildings found");
    }

    public BuildingNotFoundException(int id){
        super("No building find for the id :"+id);
    }

    public BuildingNotFoundException(String name){
        super("No building with the name "+ name +" found");
    }
}
