package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.building.BuildingDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.models.Building;

import java.util.List;

public class BuildingFacade {

    private static BuildingFacade buildingFacade;
    private BuildingDAO buildingDAO;

    private BuildingFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.buildingDAO = factory.createBuildingDAO();
    }

    public static BuildingFacade getInstance(){
        if (buildingFacade==null){
            buildingFacade = new BuildingFacade();
        }
        return buildingFacade;
    }

    public List<Building> getAllBuilding() throws BuildingNotFoundException {
        return this.buildingDAO.getAllBuilding();
    }

    public int getBuildingIdByName(String name) throws BuildingNotFoundException {
        return this.buildingDAO.getBuildingIdByName(name);
    }

    public Building getBuildingById(int id) throws BuildingNotFoundException{
        return this.buildingDAO.getBuildingById(id);
    }

    public Building getBuildingByClient(int idc) throws BuildingNotFoundException{
        return this.buildingDAO.getBuildingByClient(idc);
    }

    public Building createBuilding(Building b){
        return this.buildingDAO.createBuilding(b);
    }

    public int modifyBuilding(int id, Building b) throws BuildingNotFoundException{
        return this.buildingDAO.modifyBuilding(id, b);
    }

    public int deleteBuilding(int id) throws BuildingNotFoundException{
        return this.buildingDAO.deleteBuilding(id);
    }


}
