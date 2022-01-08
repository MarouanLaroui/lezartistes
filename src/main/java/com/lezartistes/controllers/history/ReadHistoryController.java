package com.lezartistes.controllers.history;

import com.lezartistes.models.History;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReadHistoryController extends HistoryController implements Initializable {

    History historyToShow;

    @FXML
    public Label relatedBuilding;
    @FXML
    public Label dateBuilding;
    @FXML
    public Label descriptionBuilding;

    public ReadHistoryController (History h) {
        super();
        this.historyToShow = h;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.relatedBuilding.setText(String.valueOf(this.historyToShow.getIdBuilding()));
        this.dateBuilding.setText(this.historyToShow.getDate().toString());
        this.descriptionBuilding.setText(this.historyToShow.getDescription());
    }
}
