package com.lezartistes.controllers.building;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.models.Building;

import java.io.IOException;

public class ReadBuildingController extends GeneralController {

    Building building;
    public ReadBuildingController(Building b){
        super();
        this.building=b;
    }

    public void goOnViewHistory() throws IOException {
        this.goToHistory();
    }
}
