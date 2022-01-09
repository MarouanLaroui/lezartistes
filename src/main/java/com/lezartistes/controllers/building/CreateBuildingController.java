package com.lezartistes.controllers.building;

import com.lezartistes.facades.BuildingFacade;
import com.lezartistes.models.Building;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;

public class CreateBuildingController extends BuildingController{
    @FXML
    private TextField name;

    @FXML
    private TextField region;

    @FXML
    private TextField budget;

    @FXML
    private DatePicker construction_date;

    @FXML
    private TextField master_builder;

    @FXML
    private TextField design_office;

    private BuildingFacade buildingFacade;

    @FXML
    private Label error;

    public CreateBuildingController(){
        this.buildingFacade=BuildingFacade.getInstance();
    }

    public void createBuilding(ActionEvent actionEvent){
    //TODO mettre l'id du client qui a cliqué
        if (construction_date.getValue() == null || name.getText().equals("")) {
            this.erreurCreation();
        }
        Building newBuilding = new Building(name.getText(),region.getText(),Double.valueOf(budget.getText()), new Date(construction_date.getValue().toEpochDay()), master_builder.getText(), design_office.getText(),1);

            this.buildingFacade.createBuilding(newBuilding);
    }
    public void erreurCreation () {
        this.error.setText("Error Detected.");
    }


}
