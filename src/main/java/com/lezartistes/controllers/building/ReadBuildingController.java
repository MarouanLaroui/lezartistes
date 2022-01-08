package com.lezartistes.controllers.building;

import com.lezartistes.models.Building;

import java.io.IOException;

public class ReadBuildingController extends BuildingController{

    Building building;
    public ReadBuildingController(Building b){
        super();
        this.building=b;
    }

    public void goOnViewHistory() throws IOException {
        this.redirectToHistory();
    }
}
