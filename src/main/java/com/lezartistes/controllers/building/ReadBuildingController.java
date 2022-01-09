package com.lezartistes.controllers.building;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.models.Building;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReadBuildingController extends GeneralController {
    @FXML
    private Label name;

    @FXML
    private Label region;

    @FXML
    private Label budget;

    @FXML
    private Label construction_date;

    @FXML
    private Label master_builder;

    @FXML
    private Label design_office;
    Building building;

    public ReadBuildingController(Building b){
        super();
        this.building=b;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.name.setText(this.building.getName());
        this.region.setText(this.building.getRegion());
        this.budget.setText(String.valueOf(this.building.getBudget()));
        this.construction_date.setText(this.building.getConstruction_date().toString());
        this.master_builder.setText(this.building.getMaster_builder());
        this.design_office.setText(this.building.getDesign_office());
    }

    public void goOnViewHistory() throws IOException {
        this.goToHistory();
    }
}
