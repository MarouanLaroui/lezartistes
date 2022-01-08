package com.lezartistes.controllers.building;

import com.lezartistes.App;
import com.lezartistes.controllers.GeneralController;
import com.lezartistes.facades.BuildingFacade;

import java.io.IOException;

public abstract class BuildingController extends GeneralController {

    protected final BuildingFacade buildingFacade = BuildingFacade.getInstance();

    public void redirectToBuildingList () throws IOException {
        App.setRoot("views/building/listBuilding");
    }

    public void redirectToHistory() throws IOException{
        App.setRoot("views/building/listBuilding");
    }
}
