package com.lezartistes.tests;

import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.facades.BuildingFacade;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Building;
import com.lezartistes.models.Company;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

public class TestBuilding {
    @Test
    public void addAndDeleteBuilding() throws BuildingNotFoundException {
        BuildingFacade buildingFacade = BuildingFacade.getInstance();
        int nbBuildingsBeforeAdd = buildingFacade.getAllBuilding().size();

        Building b = new Building("Teeeest","Test", 4,new Date(),"Test", "test",1);
        buildingFacade.createBuilding(b);

        Assertions.assertTrue(nbBuildingsBeforeAdd < buildingFacade.getAllBuilding().size());
        buildingFacade.deleteBuildingByName("Teeeest");
    }

}
