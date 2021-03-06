package com.lezartistes.dao.building;

import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.models.Building;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BuildingDAOPostgres extends BuildingDAO {

    private static BuildingDAOPostgres buildingDAOPostgres;
    private Connection coToDB;

    private BuildingDAOPostgres(Connection connection){
        this.coToDB=connection;
    }

    public static BuildingDAOPostgres getInstance(Connection connection){
        if (buildingDAOPostgres==null){
            buildingDAOPostgres = new BuildingDAOPostgres(connection);
        }
        return buildingDAOPostgres;
    }

    private Building resultSetToBuilding(ResultSet rs) throws SQLException{
        Building b = new Building(
                rs.getString("name"),
                rs.getString("region"),
                rs.getDouble("budget"),
                rs.getDate("construction_date"),
                rs.getString("master_building"),
                rs.getString("design_office"),
                rs.getInt("client")
        );
        b.setId(rs.getInt("id_building"));
        return b;
    }

    public List<Building> getAllBuilding() throws BuildingNotFoundException{
        List<Building> buildings = new ArrayList<>();
        String sqlSelect = "SELECT * FROM buildings";

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            ResultSet rs = pstatement.executeQuery();

            while (rs.next()){
                buildings.add(this.resultSetToBuilding(rs));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*If empty return not found exception*/
        if(buildings.isEmpty()){
            throw new BuildingNotFoundException();
        }
        return buildings;
    }

    public Building getBuildingById(int id) throws BuildingNotFoundException{
        Building building = null;
        String sqlSelect = "SELECT * FROM buildings WHERE id_Building=?";

        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le rapport si trouv??, exception sinon*/
            if(rs.next()){
                building = this.resultSetToBuilding(rs);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*Si report vide*/
        if(building == null){
            throw new BuildingNotFoundException(id);
        }
        return building;
    }

    public List<Building> getBuildingByClient(int idc) throws BuildingNotFoundException{
        List<Building> building = new ArrayList<Building>();
        String sqlSelect = "SELECT * FROM buildings WHERE client=?";

        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,idc);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le rapport si trouv??, exception sinon*/
            while(rs.next()){
                building.add(this.resultSetToBuilding(rs));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*Si report vide*/
        if(building == null){
            throw new BuildingNotFoundException(idc);
        }
        return building;
    }

    @Override
    public int getBuildingIdByName(String nameBuilding) throws BuildingNotFoundException {
        int idBuilding = -1;
        String sqlSelect = "SELECT id_building FROM buildings WHERE name=?";

        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setString(1,nameBuilding.trim());
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()){
                idBuilding = rs.getInt("id_building");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(idBuilding == -1){
            throw new BuildingNotFoundException(nameBuilding);
        }
        return idBuilding;
    }

    @Override
    public List<Building> getBuildingByMailClient(String mail) {
        List<Building> building = new ArrayList<>();
        String sqlSelect = "SELECT * " +
                "FROM buildings B " +
                "JOIN clients C ON B.client = C.id_clients " +
                "WHERE C.username = ?";

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setString(1, mail);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le rapport si trouv??, exception sinon*/
            if (rs.next()) {
                building.add(this.resultSetToBuilding(rs));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return building;
    }

    public Building createBuilding(Building b){
        PreparedStatement ps = null;
        try {
            ps = this.coToDB.prepareStatement("INSERT INTO buildings(name,region,budget,construction_date,master_building,design_office,client) VALUES (?, ?, ?, ?, ?, ?,?)");
            ps.setString(1,b.getName());
            ps.setString(2,b.getRegion());
            ps.setDouble(3,b.getBudget());
            ps.setDate(4,new java.sql.Date(b.getConstruction_date().getTime()));
            ps.setString(5,b.getMaster_builder());
            ps.setString(6,b.getDesign_office());
            ps.setInt(7,b.getClient());

            int rows = ps.executeUpdate();
            ps.close();
            System.out.println(rows);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public int deleteBuilding(int id) throws BuildingNotFoundException{
        int affectRows = 0;
        String sqlDelete = "DELETE FROM buildings WHERE id_building=?";
        try{
            PreparedStatement pstmt = this.coToDB.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int deleteBuildingByName(String name) throws BuildingNotFoundException {
        int affectRows = 0;
        String sqlDelete = "DELETE FROM buildings WHERE name=?";
        try{
            PreparedStatement pstmt = this.coToDB.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }
}
