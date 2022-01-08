package com.lezartistes.dao.building;

import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.BuildingFacade;
import com.lezartistes.models.Building;
import com.lezartistes.models.Client;

import java.io.Serializable;
import java.util.List;

public abstract class BuildingDAO implements Serializable {

    public BuildingDAO(){
        super();
    }

    public abstract List<Building> getAllBuilding() throws BuildingNotFoundException;

    public abstract Building getBuildingById(int id) throws BuildingNotFoundException;

    public abstract Building getBuildingByClient(int idc) throws BuildingNotFoundException;

    public abstract int getBuildingIdByName(String name) throws BuildingNotFoundException;

    public abstract List<Building> getBuildingByMailClient(String mail);

    public abstract Building createBuilding(Building b);

    public abstract int modifyBuilding(int id, Building b) throws BuildingNotFoundException;

    public abstract int deleteBuilding(int id) throws BuildingNotFoundException;
}
