package com.lezartistes.dao.building;

import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.models.Building;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO modify, delete et voir pour ajouter history
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
                rs.getString("master_builder"),
                rs.getString("design_office"),
                rs.getInt("client")
        );
        b.setId(rs.getInt("id"));
        return b;
    }

    public List<Building> getAllBuilding() throws BuildingNotFoundException{
        List<Building> buildings = new ArrayList<>();
        String sqlSelect = "SELECT * FROM buidlings";

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
        String sqlSelect = "SELECT * FROM buildings WHERE idBuilding=?";

        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le rapport si trouvé, exception sinon*/
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

    public Building getBuildingByClient(int idc) throws BuildingNotFoundException{
        Building building = null;
        String sqlSelect = "SELECT * FROM buildings WHERE client=?";

        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,idc);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le rapport si trouvé, exception sinon*/
            if(rs.next()){
                building = this.resultSetToBuilding(rs);
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

    public Building createBuilding(Building b){
        PreparedStatement ps = null;
        try {
            ps = this.coToDB.prepareStatement("INSERT INTO buildings(name,region,budget,construction_date,master_builder,design_office) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1,b.getName());
            ps.setString(2,b.getRegion());
            ps.setDouble(3,b.getBudget());
            ps.setDate(4,new java.sql.Date(b.getConstruction_date().getTime()));
            ps.setString(5,b.getMaster_builder());
            ps.setString(6,b.getDesign_office());

            int rows = ps.executeUpdate();
            ps.close();
            System.out.println(rows);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public int modifyBuilding(int id) throws BuildingNotFoundException{
        int affectRows = 0;
        String sqlUpdate = "UPDATE buildings SET " +
                "name = ?, " +
                "region = ? , " +
                "budget = ? " +
                "WHERE idfeedback = ?";
        try{
            PreparedStatement pstmt = this.coToDB.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, fb.getRating());
            pstmt.setString(2, fb.getComment());
            pstmt.setInt(3, fb.getCompany());
            pstmt.setInt(1, idFeedback);

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    public int deleteBuilding(int id) throws BuildingNotFoundException{
        int affectRows = 0;
        String sqlDelete = "DELETE FROM buildings WHERE idBuilding=?";
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
}