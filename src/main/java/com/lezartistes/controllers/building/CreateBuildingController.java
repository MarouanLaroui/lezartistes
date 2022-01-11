package com.lezartistes.controllers.building;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.BuildingFacade;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.Building;
import com.lezartistes.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;

public class CreateBuildingController extends GeneralController {
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

    private ClientFacade clientFacade;

    @FXML
    private Label error;

    public CreateBuildingController(){
        this.buildingFacade=BuildingFacade.getInstance();
        this.clientFacade = ClientFacade.getInstance();
    }

    public void createBuilding(ActionEvent actionEvent) throws ClientNotFoundException {
        User user = this.clientFacade.getClientByEmail(UserInformation.getUser().getMail());
        int idAuthor = this.clientFacade.getClientIdByMail(user.getMail());

        if ( name.getText().equals("")) {
            this.erreurCreation();
        }
        System.out.println("o");
        Building newBuilding = new Building(name.getText(),region.getText(),Double.valueOf(budget.getText()), new Date(construction_date.getValue().toEpochDay()), master_builder.getText(), design_office.getText(),idAuthor);
        System.out.println(newBuilding);
            this.buildingFacade.createBuilding(newBuilding);
    }
    public void erreurCreation () {
        this.error.setText("Error Detected.");
    }


}
