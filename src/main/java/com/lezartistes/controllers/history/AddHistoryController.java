package com.lezartistes.controllers.history;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.facades.BuildingFacade;
import com.lezartistes.models.Building;
import com.lezartistes.models.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddHistoryController extends HistoryController implements Initializable {

    private List<Building> buildings = new ArrayList<>();
    private final BuildingFacade buildingFacade = BuildingFacade.getInstance();
    @FXML
    private ComboBox<Building> selectRelatedBuilding;
    @FXML
    private DatePicker date_history;
    @FXML
    private TextArea description_history;
    @FXML
    private Label error;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buildings = this.buildingFacade.getBuildingByMailClient(UserInformation.getUser().getMail());
        ObservableList<Building> options = FXCollections.observableArrayList(buildings);
        selectRelatedBuilding.getItems().addAll(options);
    }

    @FXML
    public void createHistory() throws IOException {
        Building b = this.selectRelatedBuilding.getValue();
        if (date_history.getValue() == null || description_history.getText().equals("") || b == null) {
            this.erreurCreation();
        } else {
            History h = new History(b.getId(), new Date(date_history.getValue().toEpochDay()), description_history.getText().replaceAll("\n", System.getProperty("line.separator")));
            int ret = this.historyFacade.createHistory(h);
            if (ret != 0) this.redirectToHistoryList();
            else this.erreurCreation();
        }
    }

    public void erreurCreation () {
        this.error.setText("Error Detected.");
    }
}
